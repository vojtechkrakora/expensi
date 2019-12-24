package cz.krakora.expensi.repositories.impl;

import cz.krakora.expensi.models.FinancialRecord;
import cz.krakora.expensi.repositories.RecordRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public FinancialRecord getMax(boolean isIncome) {
        return getMax(isIncome, null, null);
    }

    @Override
    public FinancialRecord getMax(boolean isIncome, Date from, Date to) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FinancialRecord> cq = cb.createQuery(FinancialRecord.class);

        Root<FinancialRecord> root = cq.from(FinancialRecord.class);

        if (from != null) {
            cq.where(cb.greaterThanOrEqualTo(root.get("date"),from));
        }

        if (to != null) {
            cq.where(cb.lessThanOrEqualTo(root.get("date"),to));
        }

        if (isIncome) {
            cq.orderBy(cb.desc(root.get("amount")));
        } else {
            cq.orderBy(cb.asc(root.get("amount")));
        }
        cq.distinct(true);
        cq.select(root);

        List<FinancialRecord> resultList = em.createQuery(cq).getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }

    @Override
    public double getAverage(boolean isIncome, Date from, Date to) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<FinancialRecord> root = cq.from(FinancialRecord.class);
        cq.select(cb.avg(root.get("amount")));

        if (from != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"),from));
        }

        if (to != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"),to));
        }

        if (isIncome) {
            predicates.add(cb.greaterThan(root.get("amount"), 0));
        } else {
            predicates.add(cb.lessThan(root.get("amount"), 0));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public double getMedianAmount(boolean isIncome, Date from, Date to) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<FinancialRecord> root = cq.from(FinancialRecord.class);
        cq.select(root.get("amount"));

        if (from != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"),from));
        }

        if (to != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"),to));
        }

        if (isIncome) {
            predicates.add(cb.greaterThan(root.get("amount"), 0));
        } else {
            predicates.add(cb.lessThan(root.get("amount"), 0));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("amount")));
        List<Double> amountList = em.createQuery(cq).getResultList();

        return amountList.get(amountList.size()/2);
    }


}
