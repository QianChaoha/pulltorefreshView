package com.example.data;

import java.util.List;

import com.example.http.parser.BackResult;

public class HomeData extends BackResult{
	private List<String> picture;
	private List<String> list;
	
	public List<String> getPicture() {
		return picture;
	}

	public void setPicture(List<String> picture) {
		this.picture = picture;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	private class Content{
		private String des;
		private String downloadUrl;
		private String iconUrl;
		private String id;
		private String name;
		private String packageName;
		private String size;
		private String stars;
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
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getStars() {
			return stars;
		}
		public void setStars(String stars) {
			this.stars = stars;
		}
		
	} 
}
