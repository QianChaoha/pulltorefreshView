package com.example.googleplay.data;

import java.util.List;

public class AppInfo {
	private String des;
	private String downloadUrl;
	private String iconUrl;
	private String id;
	private String name;
	private String packageName;
	private long size;
	private float stars;
	//-------------  在DetailActivity 额外用到的数据
	private String downloadNum;
	private String version;
	private String date;
	private String author;
	private List<String> screen;
	
	public List<Safe> safe;
	
	public class Safe{
		public String safeUrl;
		public String safeDesUrl;
		public String safeDes;
		public String safeDesColor;
	}
	public String getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(String downloadNum) {
		this.downloadNum = downloadNum;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<String> getScreen() {
		return screen;
	}

	public void setScreen(List<String> screen) {
		this.screen = screen;
	}


	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}
}
