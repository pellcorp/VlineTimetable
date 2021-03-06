#
# Vline timetable model
#

CREATE TABLE LINE (ID INTEGER NOT NULL, NAME TEXT, PRIMARY KEY(ID));
CREATE TABLE STATION (ID INTEGER NOT NULL, NAME TEXT, PRIMARY KEY(ID));
CREATE TABLE LINE_STATION (
	ID INTEGER NOT NULL, 
	LINE_ID INTEGER NOT NULL, 
	STATION_ID INTEGER NOT NULL, 
	DIRECTION TEXT NOT NULL CHECK (DIRECTION IN ('I', 'O')),
PRIMARY KEY(ID));

