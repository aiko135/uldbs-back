/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.facade;

import com.penzasoft.uldbs.model.Chat;
import com.penzasoft.uldbs.model.Good;
import com.penzasoft.uldbs.model.Message;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ktepin
 */
@Stateless
public class GoodFacade {
    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;
        
    public List<Good> getAllGoods(int limit, int offset){
        limit = limit - 1;
        CriteriaQuery<Good> cq = entityManager.getCriteriaBuilder().createQuery(Good.class);
        cq.select(cq.from(Good.class));
        TypedQuery<Good> q = entityManager.createQuery(cq);
        q.setMaxResults(limit - offset + 1);
        q.setFirstResult(offset);
        return q.getResultList();
    }
}
