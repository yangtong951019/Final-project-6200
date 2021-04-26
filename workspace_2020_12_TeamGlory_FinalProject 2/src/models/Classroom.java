package models;

import java.util.ArrayList;

public class Classroom {
	private int classroomID;
	private int capacity;
	private int minAge;
	private int maxAge;
	private int groupSize;
	private int maxGroupInOneRoom;
	private ArrayList<Group> groups;
	public Classroom(int capacity,int minAge, int maxAge, int groupSize, int maxGroupInOneRoom) {
		this.capacity=capacity;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.groupSize = groupSize;
		this.maxGroupInOneRoom = maxGroupInOneRoom;
		groups=new ArrayList<Group>();
	}
	public Classroom(int classRoomID,int capacity,int minAge, int maxAge, int groupSize, int maxGroupInOneRoom) {
		this.classroomID=classRoomID;
		this.capacity=capacity;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.groupSize = groupSize;
		this.maxGroupInOneRoom = maxGroupInOneRoom;
		groups=new ArrayList<Group>();
	}
	
	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classRoomID) {
		this.classroomID = classRoomID;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int getMaxGroupInOneRoom() {
		return maxGroupInOneRoom;
	}

	public void setMaxGroupInOneRoom(int maxGroupInOneRoom) {
		this.maxGroupInOneRoom = maxGroupInOneRoom;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return classroomID+"";
	}
	
}
