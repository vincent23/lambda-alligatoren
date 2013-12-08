package de.croggle.data.model;

import android.database.sqlite.SQLiteDatabase;

public interface Persistable {
	public boolean persist(android.database.sqlite.SQLiteDatabase database);
}
