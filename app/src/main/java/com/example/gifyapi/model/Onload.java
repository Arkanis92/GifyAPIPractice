package com.example.gifyapi.model;


import com.google.gson.annotations.SerializedName;


public class Onload{

	@SerializedName("url")
	private String url;

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Onload{" + 
			"url = '" + url + '\'' + 
			"}";
		}
}