 package com.project.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.BannerRegionsResponse;
import com.project.dto.RegionBannersResponse;
import com.project.exception.ControllerException;
import com.project.exception.ServiceException;
import com.project.exception.ValidationException;
import com.project.model.Banner;
import com.project.service.BannerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class BannerController {

	Logger logger = LoggerFactory.getLogger(BannerController.class);

	@Autowired
	private BannerService bannerService;
	@Autowired
	private ValidationException validationException;

	@PostMapping("/addBanner")
	public ResponseEntity<?> createBanner(@RequestBody @Valid Banner banner) {

		try {

			logger.info("Add Banner Details: {}");
			Banner createdBanner = bannerService.saveBanner(banner);
			if (createdBanner != null) {
				return ResponseEntity.ok("Banner created successfully  :  " + banner.getBannerId());
			} else {
				return ResponseEntity.badRequest().body("Error creating banner");
			}
		}catch (ServiceException e) {
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        } catch (Exception e) {
            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        }
	}

	@PostMapping("/getregionsByBannerIds")
	public ResponseEntity<?> getRegionsByBannerId(@RequestBody Map<String, List<Integer>> request) {
		try {
			List<Integer> bannerIds = request.get("bannerIds");
			logger.info("Get regions by bannerIds: {}", bannerIds);
			List<BannerRegionsResponse> response = bannerService.getRegionsByBannerIds(bannerIds);
			return ResponseEntity.ok(response);
		}  catch (ServiceException e) {
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        } catch (Exception e) {
            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        }
	}


	@PostMapping("/getBannersByRegionIds")
    public ResponseEntity<?> getBannersByRegionId(@RequestBody Map<String, List<Integer>> request)throws ServiceException,ControllerException {
        try {
            List<Integer> regionIds = request.get("regionIds");

            logger.info("Get Banner by regionId: {}", regionIds);
            List<RegionBannersResponse> response = bannerService.getBannersByRegionId(regionIds);
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        } catch (Exception e) {
            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        }
    }


	@PutMapping("/updatebanner")
	public ResponseEntity<?> updateBanner(@RequestBody Banner banner) {
		try {
			logger.info("Update the existing banner");
			Banner updatedBanner = bannerService.updateBanner1(banner);
			if (updatedBanner != null) {
				return ResponseEntity.ok("Banner edited successfully : "+banner.getBannerId());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (ServiceException e) {
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        } catch (Exception e) {
            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        }

	}

	@DeleteMapping("/deleteBanner/{bannerId}")
	@Transactional
	public ResponseEntity<?> deleteBanner1(@PathVariable int bannerId) {
		try {
			logger.info("Delete the Banner");
			bannerService.deleteBanner(bannerId);
			return ResponseEntity.ok("Banner deleted successfully : "+bannerId);
		} catch (ServiceException e) {
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        } catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
        }
		 catch (Exception e) {
            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        }
	}

	@PostMapping("/deleteBanners")
	@Transactional

	public ResponseEntity<?> deleteBanners(@RequestBody Map<String, List<Integer>> request) {
		try {
			List<Integer> bannerIds = request.get("bannerIds");
			logger.info("Delete the Banners: {}", bannerIds);
			bannerService.deleteBanners(bannerIds);
			return ResponseEntity.ok("Banners deleted successfully : " + bannerIds);
		}  catch (ServiceException e) {
            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        } catch (Exception e) {
            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
        }

	}

	@GetMapping("/getBanner/{bannerId}")
	public ResponseEntity<?> getBannerById(@PathVariable int bannerId) {
		{
			try {
				Banner banner = bannerService.getBannerById(bannerId);
				return ResponseEntity.ok(banner);
			}  catch (ServiceException e) {
	            ControllerException ce = new ControllerException(e.getErrorcode(), e.getErrormessage());
	            return validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
	        } catch (Exception e) {
	            ControllerException ce = new ControllerException("609", "An unexpected error occurred");
	            return  validationException.handleControllerException(ce,HttpStatus.INTERNAL_SERVER_ERROR); // Throw the exception to be handled by the exception handler
	        }

		}
	}
}
