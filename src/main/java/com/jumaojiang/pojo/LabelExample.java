package com.jumaojiang.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LabelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LabelExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(String value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(String value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(String value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(String value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(String value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(String value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLike(String value) {
            addCriterion("is_del like", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotLike(String value) {
            addCriterion("is_del not like", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<String> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<String> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(String value1, String value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(String value1, String value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andKuanHaoIsNull() {
            addCriterion("kuan_hao is null");
            return (Criteria) this;
        }

        public Criteria andKuanHaoIsNotNull() {
            addCriterion("kuan_hao is not null");
            return (Criteria) this;
        }

        public Criteria andKuanHaoEqualTo(String value) {
            addCriterion("kuan_hao =", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoNotEqualTo(String value) {
            addCriterion("kuan_hao <>", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoGreaterThan(String value) {
            addCriterion("kuan_hao >", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoGreaterThanOrEqualTo(String value) {
            addCriterion("kuan_hao >=", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoLessThan(String value) {
            addCriterion("kuan_hao <", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoLessThanOrEqualTo(String value) {
            addCriterion("kuan_hao <=", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoLike(String value) {
            addCriterion("kuan_hao like", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoNotLike(String value) {
            addCriterion("kuan_hao not like", value, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoIn(List<String> values) {
            addCriterion("kuan_hao in", values, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoNotIn(List<String> values) {
            addCriterion("kuan_hao not in", values, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoBetween(String value1, String value2) {
            addCriterion("kuan_hao between", value1, value2, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andKuanHaoNotBetween(String value1, String value2) {
            addCriterion("kuan_hao not between", value1, value2, "kuanHao");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andZxbzIsNull() {
            addCriterion("zxbz is null");
            return (Criteria) this;
        }

        public Criteria andZxbzIsNotNull() {
            addCriterion("zxbz is not null");
            return (Criteria) this;
        }

        public Criteria andZxbzEqualTo(String value) {
            addCriterion("zxbz =", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzNotEqualTo(String value) {
            addCriterion("zxbz <>", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzGreaterThan(String value) {
            addCriterion("zxbz >", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzGreaterThanOrEqualTo(String value) {
            addCriterion("zxbz >=", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzLessThan(String value) {
            addCriterion("zxbz <", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzLessThanOrEqualTo(String value) {
            addCriterion("zxbz <=", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzLike(String value) {
            addCriterion("zxbz like", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzNotLike(String value) {
            addCriterion("zxbz not like", value, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzIn(List<String> values) {
            addCriterion("zxbz in", values, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzNotIn(List<String> values) {
            addCriterion("zxbz not in", values, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzBetween(String value1, String value2) {
            addCriterion("zxbz between", value1, value2, "zxbz");
            return (Criteria) this;
        }

        public Criteria andZxbzNotBetween(String value1, String value2) {
            addCriterion("zxbz not between", value1, value2, "zxbz");
            return (Criteria) this;
        }

        public Criteria andCplbIsNull() {
            addCriterion("cplb is null");
            return (Criteria) this;
        }

        public Criteria andCplbIsNotNull() {
            addCriterion("cplb is not null");
            return (Criteria) this;
        }

        public Criteria andCplbEqualTo(String value) {
            addCriterion("cplb =", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbNotEqualTo(String value) {
            addCriterion("cplb <>", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbGreaterThan(String value) {
            addCriterion("cplb >", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbGreaterThanOrEqualTo(String value) {
            addCriterion("cplb >=", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbLessThan(String value) {
            addCriterion("cplb <", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbLessThanOrEqualTo(String value) {
            addCriterion("cplb <=", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbLike(String value) {
            addCriterion("cplb like", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbNotLike(String value) {
            addCriterion("cplb not like", value, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbIn(List<String> values) {
            addCriterion("cplb in", values, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbNotIn(List<String> values) {
            addCriterion("cplb not in", values, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbBetween(String value1, String value2) {
            addCriterion("cplb between", value1, value2, "cplb");
            return (Criteria) this;
        }

        public Criteria andCplbNotBetween(String value1, String value2) {
            addCriterion("cplb not between", value1, value2, "cplb");
            return (Criteria) this;
        }

        public Criteria andOutFabricIsNull() {
            addCriterion("out_fabric is null");
            return (Criteria) this;
        }

        public Criteria andOutFabricIsNotNull() {
            addCriterion("out_fabric is not null");
            return (Criteria) this;
        }

        public Criteria andOutFabricEqualTo(String value) {
            addCriterion("out_fabric =", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricNotEqualTo(String value) {
            addCriterion("out_fabric <>", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricGreaterThan(String value) {
            addCriterion("out_fabric >", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricGreaterThanOrEqualTo(String value) {
            addCriterion("out_fabric >=", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricLessThan(String value) {
            addCriterion("out_fabric <", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricLessThanOrEqualTo(String value) {
            addCriterion("out_fabric <=", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricLike(String value) {
            addCriterion("out_fabric like", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricNotLike(String value) {
            addCriterion("out_fabric not like", value, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricIn(List<String> values) {
            addCriterion("out_fabric in", values, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricNotIn(List<String> values) {
            addCriterion("out_fabric not in", values, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricBetween(String value1, String value2) {
            addCriterion("out_fabric between", value1, value2, "outFabric");
            return (Criteria) this;
        }

        public Criteria andOutFabricNotBetween(String value1, String value2) {
            addCriterion("out_fabric not between", value1, value2, "outFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricIsNull() {
            addCriterion("int_fabric is null");
            return (Criteria) this;
        }

        public Criteria andIntFabricIsNotNull() {
            addCriterion("int_fabric is not null");
            return (Criteria) this;
        }

        public Criteria andIntFabricEqualTo(String value) {
            addCriterion("int_fabric =", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricNotEqualTo(String value) {
            addCriterion("int_fabric <>", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricGreaterThan(String value) {
            addCriterion("int_fabric >", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricGreaterThanOrEqualTo(String value) {
            addCriterion("int_fabric >=", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricLessThan(String value) {
            addCriterion("int_fabric <", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricLessThanOrEqualTo(String value) {
            addCriterion("int_fabric <=", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricLike(String value) {
            addCriterion("int_fabric like", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricNotLike(String value) {
            addCriterion("int_fabric not like", value, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricIn(List<String> values) {
            addCriterion("int_fabric in", values, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricNotIn(List<String> values) {
            addCriterion("int_fabric not in", values, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricBetween(String value1, String value2) {
            addCriterion("int_fabric between", value1, value2, "intFabric");
            return (Criteria) this;
        }

        public Criteria andIntFabricNotBetween(String value1, String value2) {
            addCriterion("int_fabric not between", value1, value2, "intFabric");
            return (Criteria) this;
        }

        public Criteria andFillerIsNull() {
            addCriterion("filler is null");
            return (Criteria) this;
        }

        public Criteria andFillerIsNotNull() {
            addCriterion("filler is not null");
            return (Criteria) this;
        }

        public Criteria andFillerEqualTo(String value) {
            addCriterion("filler =", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerNotEqualTo(String value) {
            addCriterion("filler <>", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerGreaterThan(String value) {
            addCriterion("filler >", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerGreaterThanOrEqualTo(String value) {
            addCriterion("filler >=", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerLessThan(String value) {
            addCriterion("filler <", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerLessThanOrEqualTo(String value) {
            addCriterion("filler <=", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerLike(String value) {
            addCriterion("filler like", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerNotLike(String value) {
            addCriterion("filler not like", value, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerIn(List<String> values) {
            addCriterion("filler in", values, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerNotIn(List<String> values) {
            addCriterion("filler not in", values, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerBetween(String value1, String value2) {
            addCriterion("filler between", value1, value2, "filler");
            return (Criteria) this;
        }

        public Criteria andFillerNotBetween(String value1, String value2) {
            addCriterion("filler not between", value1, value2, "filler");
            return (Criteria) this;
        }

        public Criteria andCheckerIsNull() {
            addCriterion("checker is null");
            return (Criteria) this;
        }

        public Criteria andCheckerIsNotNull() {
            addCriterion("checker is not null");
            return (Criteria) this;
        }

        public Criteria andCheckerEqualTo(String value) {
            addCriterion("checker =", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotEqualTo(String value) {
            addCriterion("checker <>", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerGreaterThan(String value) {
            addCriterion("checker >", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerGreaterThanOrEqualTo(String value) {
            addCriterion("checker >=", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLessThan(String value) {
            addCriterion("checker <", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLessThanOrEqualTo(String value) {
            addCriterion("checker <=", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLike(String value) {
            addCriterion("checker like", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotLike(String value) {
            addCriterion("checker not like", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerIn(List<String> values) {
            addCriterion("checker in", values, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotIn(List<String> values) {
            addCriterion("checker not in", values, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerBetween(String value1, String value2) {
            addCriterion("checker between", value1, value2, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotBetween(String value1, String value2) {
            addCriterion("checker not between", value1, value2, "checker");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNull() {
            addCriterion("bar_code is null");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNotNull() {
            addCriterion("bar_code is not null");
            return (Criteria) this;
        }

        public Criteria andBarCodeEqualTo(String value) {
            addCriterion("bar_code =", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotEqualTo(String value) {
            addCriterion("bar_code <>", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThan(String value) {
            addCriterion("bar_code >", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bar_code >=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThan(String value) {
            addCriterion("bar_code <", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThanOrEqualTo(String value) {
            addCriterion("bar_code <=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLike(String value) {
            addCriterion("bar_code like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotLike(String value) {
            addCriterion("bar_code not like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeIn(List<String> values) {
            addCriterion("bar_code in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotIn(List<String> values) {
            addCriterion("bar_code not in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeBetween(String value1, String value2) {
            addCriterion("bar_code between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotBetween(String value1, String value2) {
            addCriterion("bar_code not between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIsNull() {
            addCriterion("guide_price is null");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIsNotNull() {
            addCriterion("guide_price is not null");
            return (Criteria) this;
        }

        public Criteria andGuidePriceEqualTo(BigDecimal value) {
            addCriterion("guide_price =", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotEqualTo(BigDecimal value) {
            addCriterion("guide_price <>", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceGreaterThan(BigDecimal value) {
            addCriterion("guide_price >", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("guide_price >=", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceLessThan(BigDecimal value) {
            addCriterion("guide_price <", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("guide_price <=", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIn(List<BigDecimal> values) {
            addCriterion("guide_price in", values, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotIn(List<BigDecimal> values) {
            addCriterion("guide_price not in", values, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("guide_price between", value1, value2, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("guide_price not between", value1, value2, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNull() {
            addCriterion("sell_price is null");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNotNull() {
            addCriterion("sell_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellPriceEqualTo(BigDecimal value) {
            addCriterion("sell_price =", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotEqualTo(BigDecimal value) {
            addCriterion("sell_price <>", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThan(BigDecimal value) {
            addCriterion("sell_price >", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_price >=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThan(BigDecimal value) {
            addCriterion("sell_price <", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_price <=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIn(List<BigDecimal> values) {
            addCriterion("sell_price in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotIn(List<BigDecimal> values) {
            addCriterion("sell_price not in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_price between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_price not between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNull() {
            addCriterion("buy_price is null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNotNull() {
            addCriterion("buy_price is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceEqualTo(BigDecimal value) {
            addCriterion("buy_price =", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotEqualTo(BigDecimal value) {
            addCriterion("buy_price <>", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThan(BigDecimal value) {
            addCriterion("buy_price >", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buy_price >=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThan(BigDecimal value) {
            addCriterion("buy_price <", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buy_price <=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIn(List<BigDecimal> values) {
            addCriterion("buy_price in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotIn(List<BigDecimal> values) {
            addCriterion("buy_price not in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buy_price between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buy_price not between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andIsSelledIsNull() {
            addCriterion("is_selled is null");
            return (Criteria) this;
        }

        public Criteria andIsSelledIsNotNull() {
            addCriterion("is_selled is not null");
            return (Criteria) this;
        }

        public Criteria andIsSelledEqualTo(Integer value) {
            addCriterion("is_selled =", value, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledNotEqualTo(Integer value) {
            addCriterion("is_selled <>", value, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledGreaterThan(Integer value) {
            addCriterion("is_selled >", value, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_selled >=", value, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledLessThan(Integer value) {
            addCriterion("is_selled <", value, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledLessThanOrEqualTo(Integer value) {
            addCriterion("is_selled <=", value, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledIn(List<Integer> values) {
            addCriterion("is_selled in", values, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledNotIn(List<Integer> values) {
            addCriterion("is_selled not in", values, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledBetween(Integer value1, Integer value2) {
            addCriterion("is_selled between", value1, value2, "isSelled");
            return (Criteria) this;
        }

        public Criteria andIsSelledNotBetween(Integer value1, Integer value2) {
            addCriterion("is_selled not between", value1, value2, "isSelled");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Integer value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Integer value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Integer value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Integer value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Integer> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Integer> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andBlank1IsNull() {
            addCriterion("blank1 is null");
            return (Criteria) this;
        }

        public Criteria andBlank1IsNotNull() {
            addCriterion("blank1 is not null");
            return (Criteria) this;
        }

        public Criteria andBlank1EqualTo(String value) {
            addCriterion("blank1 =", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1NotEqualTo(String value) {
            addCriterion("blank1 <>", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1GreaterThan(String value) {
            addCriterion("blank1 >", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1GreaterThanOrEqualTo(String value) {
            addCriterion("blank1 >=", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1LessThan(String value) {
            addCriterion("blank1 <", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1LessThanOrEqualTo(String value) {
            addCriterion("blank1 <=", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1Like(String value) {
            addCriterion("blank1 like", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1NotLike(String value) {
            addCriterion("blank1 not like", value, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1In(List<String> values) {
            addCriterion("blank1 in", values, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1NotIn(List<String> values) {
            addCriterion("blank1 not in", values, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1Between(String value1, String value2) {
            addCriterion("blank1 between", value1, value2, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank1NotBetween(String value1, String value2) {
            addCriterion("blank1 not between", value1, value2, "blank1");
            return (Criteria) this;
        }

        public Criteria andBlank2IsNull() {
            addCriterion("blank2 is null");
            return (Criteria) this;
        }

        public Criteria andBlank2IsNotNull() {
            addCriterion("blank2 is not null");
            return (Criteria) this;
        }

        public Criteria andBlank2EqualTo(String value) {
            addCriterion("blank2 =", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2NotEqualTo(String value) {
            addCriterion("blank2 <>", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2GreaterThan(String value) {
            addCriterion("blank2 >", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2GreaterThanOrEqualTo(String value) {
            addCriterion("blank2 >=", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2LessThan(String value) {
            addCriterion("blank2 <", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2LessThanOrEqualTo(String value) {
            addCriterion("blank2 <=", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2Like(String value) {
            addCriterion("blank2 like", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2NotLike(String value) {
            addCriterion("blank2 not like", value, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2In(List<String> values) {
            addCriterion("blank2 in", values, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2NotIn(List<String> values) {
            addCriterion("blank2 not in", values, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2Between(String value1, String value2) {
            addCriterion("blank2 between", value1, value2, "blank2");
            return (Criteria) this;
        }

        public Criteria andBlank2NotBetween(String value1, String value2) {
            addCriterion("blank2 not between", value1, value2, "blank2");
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