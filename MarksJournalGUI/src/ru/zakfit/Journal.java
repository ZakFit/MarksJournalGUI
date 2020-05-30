package ru.zakfit;

import java.util.ArrayList;

public class Journal {
	private ArrayList<Pupil> pupils;

	public ArrayList<Pupil> getPupils() {
		return pupils;
	}

	public void setPupils(ArrayList<Pupil> pupils) {
		this.pupils = pupils;
	}
	// ��������� ������� � ����� ������
	public void addPupil(Pupil newPupil) {
		this.pupils.add(newPupil);
	}
	// ��������� ������� � ������������ �������
	public void insertPupil(Pupil newPupil, int pos){
		this.pupils.add(pos, newPupil);
	}
	//�������� ������� �� ������� 
	public Pupil getPupilByIndex(int pos) {
			return this.pupils.get(pos);
		}
}
