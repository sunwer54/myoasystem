package com.oa.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IncomeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IncomeExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIcidIsNull() {
            addCriterion("icid is null");
            return (Criteria) this;
        }

        public Criteria andIcidIsNotNull() {
            addCriterion("icid is not null");
            return (Criteria) this;
        }

        public Criteria andIcidEqualTo(Integer value) {
            addCriterion("icid =", value, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidNotEqualTo(Integer value) {
            addCriterion("icid <>", value, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidGreaterThan(Integer value) {
            addCriterion("icid >", value, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidGreaterThanOrEqualTo(Integer value) {
            addCriterion("icid >=", value, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidLessThan(Integer value) {
            addCriterion("icid <", value, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidLessThanOrEqualTo(Integer value) {
            addCriterion("icid <=", value, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidIn(List<Integer> values) {
            addCriterion("icid in", values, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidNotIn(List<Integer> values) {
            addCriterion("icid not in", values, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidBetween(Integer value1, Integer value2) {
            addCriterion("icid between", value1, value2, "icid");
            return (Criteria) this;
        }

        public Criteria andIcidNotBetween(Integer value1, Integer value2) {
            addCriterion("icid not between", value1, value2, "icid");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andIcdateIsNull() {
            addCriterion("icdate is null");
            return (Criteria) this;
        }

        public Criteria andIcdateIsNotNull() {
            addCriterion("icdate is not null");
            return (Criteria) this;
        }

        public Criteria andIcdateEqualTo(Date value) {
            addCriterionForJDBCDate("icdate =", value, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("icdate <>", value, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateGreaterThan(Date value) {
            addCriterionForJDBCDate("icdate >", value, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("icdate >=", value, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateLessThan(Date value) {
            addCriterionForJDBCDate("icdate <", value, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("icdate <=", value, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateIn(List<Date> values) {
            addCriterionForJDBCDate("icdate in", values, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("icdate not in", values, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("icdate between", value1, value2, "icdate");
            return (Criteria) this;
        }

        public Criteria andIcdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("icdate not between", value1, value2, "icdate");
            return (Criteria) this;
        }

        public Criteria andIctypeIsNull() {
            addCriterion("ictype is null");
            return (Criteria) this;
        }

        public Criteria andIctypeIsNotNull() {
            addCriterion("ictype is not null");
            return (Criteria) this;
        }

        public Criteria andIctypeEqualTo(String value) {
            addCriterion("ictype =", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeNotEqualTo(String value) {
            addCriterion("ictype <>", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeGreaterThan(String value) {
            addCriterion("ictype >", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeGreaterThanOrEqualTo(String value) {
            addCriterion("ictype >=", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeLessThan(String value) {
            addCriterion("ictype <", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeLessThanOrEqualTo(String value) {
            addCriterion("ictype <=", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeLike(String value) {
            addCriterion("ictype like", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeNotLike(String value) {
            addCriterion("ictype not like", value, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeIn(List<String> values) {
            addCriterion("ictype in", values, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeNotIn(List<String> values) {
            addCriterion("ictype not in", values, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeBetween(String value1, String value2) {
            addCriterion("ictype between", value1, value2, "ictype");
            return (Criteria) this;
        }

        public Criteria andIctypeNotBetween(String value1, String value2) {
            addCriterion("ictype not between", value1, value2, "ictype");
            return (Criteria) this;
        }

        public Criteria andIndescIsNull() {
            addCriterion("indesc is null");
            return (Criteria) this;
        }

        public Criteria andIndescIsNotNull() {
            addCriterion("indesc is not null");
            return (Criteria) this;
        }

        public Criteria andIndescEqualTo(String value) {
            addCriterion("indesc =", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescNotEqualTo(String value) {
            addCriterion("indesc <>", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescGreaterThan(String value) {
            addCriterion("indesc >", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescGreaterThanOrEqualTo(String value) {
            addCriterion("indesc >=", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescLessThan(String value) {
            addCriterion("indesc <", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescLessThanOrEqualTo(String value) {
            addCriterion("indesc <=", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescLike(String value) {
            addCriterion("indesc like", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescNotLike(String value) {
            addCriterion("indesc not like", value, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescIn(List<String> values) {
            addCriterion("indesc in", values, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescNotIn(List<String> values) {
            addCriterion("indesc not in", values, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescBetween(String value1, String value2) {
            addCriterion("indesc between", value1, value2, "indesc");
            return (Criteria) this;
        }

        public Criteria andIndescNotBetween(String value1, String value2) {
            addCriterion("indesc not between", value1, value2, "indesc");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}