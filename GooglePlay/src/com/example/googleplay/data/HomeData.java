package com.example.googleplay.data;

import java.util.List;

import com.example.googleplay.http.parser.BackResult;

public class HomeData extends BackResult{
	private List<String> picture;
	private List<AppInfo> list;
	
	public List<String> getPicture() {
		return picture;
	}

	public void setPicture(List<String> picture) {
		this.picture = picture;
	}


	public List<AppInfo> getList() {
		return list;
	}

	public void setList(List<AppInfo> list) {
		this.list = list;
	}

	public class AppInfo{
		private String des;
		private String downloadUrl;
		private String iconUrl;
		private String id;
		private String name;
		private String packageName;
		private long size;
		private float stars;
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
}
