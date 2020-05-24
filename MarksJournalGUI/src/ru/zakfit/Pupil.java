package ru.zakfit;
/** Класс ученика
 *  содержит свойства:
 *  pupNumber
 *  pupMarks
 *  avgMark
 */
public class Pupil {
	private Integer 	pupNumber;
	private Integer[]  	pupMarks;
	private Double		avgMark;	

	public Integer getPupNumber() {
		return pupNumber;
	}

	public void setPupNumber(Integer pupNumber) {
		this.pupNumber = pupNumber;
	}

	public Integer[] getPupMarks() {
		return pupMarks;
	}

	public void setPupMarks(Integer[] pupMarks) {
		this.pupMarks = pupMarks;
	}

	public Double getAvgMark() {
		return avgMark;
	}
// To-Do - реализовать вычисление среднего балла
	public void calcAvgMark() {
		Double tmpAvgMark = 0.0;
		this.avgMark = tmpAvgMark;
	}
	
}
