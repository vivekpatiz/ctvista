package com.danaherdigital.che_hx.report_management.service;

import com.danaherdigital.che_hx.report_management.dto.UserPreferenceDTO;

public interface UserPreferenceService {

	void createUserPreference(UserPreferenceDTO userPreferenceDTO);

	UserPreferenceDTO getUserPreferenceById(String assetId, String userName);

}
