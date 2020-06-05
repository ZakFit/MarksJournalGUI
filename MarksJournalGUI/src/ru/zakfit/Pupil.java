package ru.zakfit;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Класс ученика
 *  содержит свойства:
 *  pupNumber
 *  pupMarks
 *  avgMark
 */
public class Pupil {
	private Integer pupNumber;
	private Integer[] pupMarks;
	private Double avgMark;	

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
	// округление до 2 знаков
	private Double roundDblTo2(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public void calcAvgMark() {
		Double tmpAvgMark = 0.0;
		int cnt = 0;
		double sum = 0.0;
		for (int i = 0; i < pupMarks.length; i++) {
			if (pupMarks[i] > 0) {
				cnt++;
				sum = sum + pupMarks[i];
			} 
		}
		if (cnt>0) {
			tmpAvgMark = roundDblTo2(sum/cnt, 2);
			this.avgMark = tmpAvgMark;
		}
		else {
			tmpAvgMark = 0.0;
			this.avgMark = tmpAvgMark;
		}
			  		 
	}
}
	

