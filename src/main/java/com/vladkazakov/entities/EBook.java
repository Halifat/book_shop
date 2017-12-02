package com.vladkazakov.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EBook")
public class EBook extends Product {
	private String company;
	private int batteryLife;
	private int sizeInch;

	public EBook() {
	}

	public String toString() {
		return (id + ";" + name + ";" + company + ";" + batteryLife + ";" + sizeInch);
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public String getCompany() {
		return company;
	}

	public int getSizeInch() {
		return sizeInch;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setSizeInch(int sizeInch) {
		this.sizeInch = sizeInch;
	}

	@Override
	public String getDescription() {
		String result = "Компания: " + company + ", объем батареи: " + batteryLife + ", размер экрана(дюймов): " + sizeInch + ".";
		return result;
	}

}
