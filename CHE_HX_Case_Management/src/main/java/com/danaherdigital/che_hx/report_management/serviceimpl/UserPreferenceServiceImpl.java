package com.danaherdigital.che_hx.report_management.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.report_management.dto.UserPreferenceDTO;
import com.danaherdigital.che_hx.report_management.model.UserPreferences;
import com.danaherdigital.che_hx.report_management.repository.UserPreferenceRepository;
import com.danaherdigital.che_hx.report_management.service.UserPreferenceService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

	@Autowired
	private UserPreferenceRepository userPreferenceRepository;

	static final Logger LOGGER = LoggerFactory.getLogger(UserPreferenceServiceImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createUserPreference(UserPreferenceDTO userPreferenceDTO) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "saveorUpdate");
		LOGGER.info("Saving createUserPreference");
		Optional<UserPreferences> optUserPreferences = userPreferenceRepository
				.findByUserNameAndAssetId(userPreferenceDTO.getUserName(), userPreferenceDTO.getAssetId());

		UserPreferences newUserPreferences = modelMapper.map(userPreferenceDTO, UserPreferences.class);
		if (!optUserPreferences.isPresent()) {
			newUserPreferences.setCreatedBy(userPreferenceDTO.getUserName());
			newUserPreferences.setUpdatedBy(userPreferenceDTO.getUserName());
			userPreferenceRepository.save(newUserPreferences);
		} else {
				newUserPreferences.setId(optUserPreferences.get().getId());
				newUserPreferences.setCreatedBy(userPreferenceDTO.getUserName());
				newUserPreferences.setUpdatedBy(userPreferenceDTO.getUserName());
			userPreferenceRepository.save(newUserPreferences);
		}

		LOGGER.info("Saved createUserPreference");
	}

	@Override
	public UserPreferenceDTO getUserPreferenceById(String assetId, String userName) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getUserPreferenceById");
		LOGGER.info("getUserPreferenceById started");
		Optional<UserPreferences> optUserPreferences = userPreferenceRepository.findByUserNameAndAssetId(userName,
				assetId);
		if (optUserPreferences.isPresent()) {
			LOGGER.info("getUserPreferenceById done");
			return modelMapper.map(optUserPreferences.get(), UserPreferenceDTO.class);
		} else {
			LOGGER.info("getUserPreferenceById done");
			return new UserPreferenceDTO();
		}

	}

}
