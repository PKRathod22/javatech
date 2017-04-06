package com.pk.aspect;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.pk.model.SystemTrack;

public class SystemTrackUpdatorInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		for (int i = 0; i < state.length; i++) {
			if (propertyNames[i].equals("systemTrack")) {
				SystemTrack systemTrack = new SystemTrack();
				Date currentLocationTime = new Date();
				systemTrack.setCreateDate(currentLocationTime);
				systemTrack.setCreateUser("Praveen");
				systemTrack.setLastUpdatedDate(currentLocationTime);
				systemTrack.setLastUpdatedUser("praveen");
				state[i] = systemTrack;
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		for (int i = 0; i < currentState.length; i++) {
			if (propertyNames[i].equals("systemTrack")) {
				Date currentLocationTime = new Date();

				SystemTrack systemTrack = (SystemTrack) previousState[i];
				/*
				 * systemTrack.setCreateDate(currentLocationTime);
				 * systemTrack.setCreateUser("ADMIN");
				 */
				systemTrack.setLastUpdatedDate(currentLocationTime);
				systemTrack.setLastUpdatedUser("praveen");
				currentState[i] = systemTrack;
				return true;
			}
		}

		return false;

	}

	@Override
	public void preFlush(Iterator entities) {
		super.preFlush(entities);
	}

	@Override
	public void postFlush(Iterator entities) {
		super.postFlush(entities);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		super.onDelete(entity, id, state, propertyNames, types);
	}
}
