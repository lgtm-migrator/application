package org.fornever.example.model

enum ExampleHouseState {

	NEW(0, "NEW", "New instance"),
	BUILDING(1, "BUILDING", "In building"),
	EMPTY(2, "EMPTY", "No user to use"),
	INUSE(3, "INUSE", "User live in it"),
	TRANSFERING(4, "TRANSFERING", "Transferring from one to one"),
	ARCHIVE(5, "ARCHIVE", "Archived by some reason"),
	DESTROY(6, "DESTROY", "Destroyed"),

	private final Integer code;

	private final String name;

	private final String description;

	private ExampleHouseState(Integer code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public static ExampleHouseState getStateByCode(Integer code) {
		for (c in ExampleHouseState.values()) {
			if (c.code == code) {
				return c;
			}
		}
		return null;
	}
	
}
