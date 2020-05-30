package ru.zakfit;

import java.util.ArrayList;

public class Journal {
	private int maxPupils;
	private int maxMarks;
	
	private ArrayList<Pupil> pupils;

	public ArrayList<Pupil> getPupils() {
		return pupils;
	}

	public void setPupils(ArrayList<Pupil> pupils) {
		this.pupils = pupils;
	}
	// добавляем ученика в конец списка
	public void addPupil(Pupil newPupil) {
		this.pupils.add(newPupil);
	}
	// добавляем ученика в определенную позицию
	public void insertPupil(Pupil newPupil, int pos){
		this.pupils.add(pos, newPupil);
	}
	//получаем ученика по индексу 
	public Pupil getPupilByIndex(int pos) {
			return this.pupils.get(pos);
		}

	public int getMaxPupils() {
		return maxPupils;
	}

	public void setMaxPupils(int maxPupils) {
		this.maxPupils = maxPupils;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}
}
