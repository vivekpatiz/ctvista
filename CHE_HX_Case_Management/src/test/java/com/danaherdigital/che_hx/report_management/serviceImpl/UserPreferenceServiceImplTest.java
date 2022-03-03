package com.danaherdigital.che_hx.report_management.serviceImpl;


import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.danaherdigital.che_hx.report_management.dto.UserPreferenceDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ConflictException;
import com.danaherdigital.che_hx.report_management.model.UserPreferences;
import com.danaherdigital.che_hx.report_management.repository.UserPreferenceRepository;
import com.danaherdigital.che_hx.report_management.serviceimpl.UserPreferenceServiceImpl;
import com.danaherdigital.che_hx.report_management.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserPreferenceServiceImplTest {


	@InjectMocks
	UserPreferenceServiceImpl userPreferenceServiceImpl;

	@Mock
	UserPreferenceRepository userPreferenceRepository;

	@Mock
	ModelMapper modelMapper;
	private UserPreferences userPreferences = new UserPreferences();


	  @Before
      public void setUp() {
          when(modelMapper.map(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(userPreferences);

      }

	@Test
	public void getUserPreferenceByIdTestOK() throws ChemtreatException {
		 when(modelMapper.map(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(TestUtils.getUserPreferenceDTOTemplate());
		String assetID = "12312";
 		String userName = "Kevin";
 		 given(userPreferenceRepository.findByUserNameAndAssetId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).willReturn(TestUtils.getUserPreferenceTemplate());
		 UserPreferenceDTO  userPreferenceDTO = userPreferenceServiceImpl.getUserPreferenceById(assetID, userName);
		assertEquals("12312",userPreferenceDTO.getId());

	}

	@Test
	public void getUserPreferenceByIdTestElseOK() throws ChemtreatException {

		String assetID = "12312";
 		String userName = "Kevin";
 		 given(userPreferenceRepository.findByUserNameAndAssetId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).willReturn(Optional.empty());

		 UserPreferenceDTO  userPreferenceDTO = userPreferenceServiceImpl.getUserPreferenceById(assetID, userName);
		assertEquals(null,userPreferenceDTO.getAssetId());

	}


	@Test
	public void saveUserPreferenceOKTest() throws ChemtreatException, ConflictException {

		String reportName = "422";

		 given(userPreferenceRepository.findByUserNameAndAssetId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).willReturn(TestUtils.getUserPreferenceTemplate());

		 userPreferenceServiceImpl.createUserPreference(TestUtils.getUserPreferenceDTOTemplate());
		 assertEquals("422", reportName);
	}


	@Test
	public void saveUserPreferenceElseOKTest() throws ChemtreatException, ConflictException {

		String reportName = "422";

		 given(userPreferenceRepository.findByUserNameAndAssetId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).willReturn(Optional.empty());

		 userPreferenceServiceImpl.createUserPreference(TestUtils.getUserPreferenceDTOTemplate());
		 assertEquals("422", reportName);
	}


}