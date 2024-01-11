package com.Bosonit.Block7CrudValidation.repository;

import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block7CrudValidation.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.Bosonit.Block7CrudValidation.controllers.ControllerPersonaGet.*;

public class PersonaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<PersonaOutputDto> findPersonaByQuery(Map<String, Object> conditions, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = buildPredicates(cb, root, conditions);
        query.where(predicates.toArray(new Predicate[0]));

        if (pageable.getSort().isSorted()) {
            query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
        }

        TypedQuery<Persona> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<PersonaOutputDto> personasOutputDto = typedQuery.getResultList()
                .stream()
                .map(Persona::personaToPersonaOutputDto)
                .toList();

        Long totalCount = countTotalRecords(cb, conditions);

        return new PageImpl<>(personasOutputDto, pageable, totalCount);
    }

    // Construimos los predicates en funcion de los parametros proporcionados a la query
    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Persona> root, Map<String, Object> conditions) {
        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value) -> {
            switch (field) {
                case "idPersona":
                    predicates.add(cb.equal(root.get(field), (Integer) value));
                    break;
                case "usuario":
                case "name":
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "createdDate":
                    String dateCondition = (String) conditions.get("dateCondition");
                    Path<Date> datePath = root.get(field);
                    switch (dateCondition) {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(datePath, (Date) value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(datePath, (Date) value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(datePath, (Date) value));
                            break;
                    }
                    break;
            }
        });
        return predicates;
    }

    //Función para el conteo de registros
    private Long countTotalRecords(CriteriaBuilder cb, Map<String, Object> conditions) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Persona> countRoot = countQuery.from(Persona.class);
        countQuery.select(cb.count(countRoot));

        List<Predicate> countPredicates = buildPredicates(cb, countRoot, conditions);
        countQuery.where(countPredicates.toArray(new Predicate[0]));

        TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQuery);
        return countTypedQuery.getSingleResult();
    }

}
