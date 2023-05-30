//package com.project.dto;
//
//import java.util.List;
//
//import com.project.enumconfig.BannerPosition;
//import com.project.enumconfig.BannerSize;
//import com.project.enumconfig.BannerType;
//import com.project.enumconfig.DirectionOfRotation;
////import com.project.model.Banner;
//import com.project.model.Region;
//import com.project.model.TargetAudience;
//import com.project.model.TargetAudienceException;
//import com.project.model.UploadImage;
//
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Banner {
//	
//	
//	private int bannerId;
//
//	
//	private String merchantID;
//	
//	private String c2Code;
//	@NotNull(message="banner title is required")
//	
//	private String bannerTitle;
//
//	private String bannerSubtitle;
//
//	private String bannerDescription;
//	@NotNull(message="banner Type is required")
//
//	
//	
//	private BannerType bannerType;
//	@NotNull(message="banner size is required")
//
//	
//	private BannerSize bannerSize;
//
//	private String bannerStartDate;
//	
//	private String bannerEndDate;
//	
//	private String bannerEndtime;
//	
//	private String CTAURL;
//	
//	
//	private DirectionOfRotation directionofRotation;
//	
//	private int frequencyofRotation;
//	
//	private boolean whetherenabletimer;
//
//	private String timerStartTime;
//	
//	private String timerDuration;
//	
//	private String createdBy;
//	
//	private String editedBy;
//	
//	private String createdTime;
//
//	private String editedTime;
//	
//	private BannerPosition bannerPosition;
//	
//	private boolean deleteFlag = false;
//
//}
