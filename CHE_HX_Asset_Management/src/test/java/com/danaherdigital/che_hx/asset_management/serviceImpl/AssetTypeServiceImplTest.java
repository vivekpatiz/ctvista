package com.danaherdigital.che_hx.asset_management.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.danaherdigital.che_hx.asset_management.dto.AssetTypeModelDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.danaherdigital.che_hx.asset_management.respository.AssetTypeRepository;
import com.danaherdigital.che_hx.asset_management.serviceimpl.AssetTypeServiceImpl;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.asset_management.utils.AssetTypeTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class AssetTypeServiceImplTest {

	@InjectMocks
	AssetTypeServiceImpl assetTypeServiceImpl;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	AssetTypeRepository assetTypeRepository;

	@Test
	public void createAssetTypeTest() throws ChemtreatException {
		AssetType a = new AssetType();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		String assetType_id = assetTypeServiceImpl.saveAssetType(AssetTypeTestUtil.getAssetTypeModelTemplate());
		assertEquals("e9b1df36-16c0-47ac-bc46-9e991f3959be",assetType_id);

	}

	@Test
	public void createAssetTypeDuplicateTest() throws ChemtreatException {
		AssetType a = new AssetType();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(a));
		Exception exception = assertThrows(ChemtreatException.class, () -> {
			assetTypeServiceImpl.saveAssetType(AssetTypeTestUtil.getAssetTypeModelTemplate());
	    });

	    String expectedMessage = ApplicationConstants.ASSET_TYPE_ALREADY_EXISTS;
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAssetTypeByIdTest() throws ChemtreatException {
		AssetTypeModelDTO a = new AssetTypeModelDTO();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		AssetType at= new ModelMapper().map(a, AssetType.class);
		when(assetTypeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(at));

		// when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		AssetType assetType = assetTypeServiceImpl.getAssetTypeById(a.getId());
		assertEquals("e9b1df36-16c0-47ac-bc46-9e991f3959be",assetType.getId());

	}

	@Test(expected = ResourceNotFoundException.class)
	public void getAssetTypeByIdExceptionTest() throws ResourceNotFoundException {
		AssetType a = new AssetType();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetTypeRepository.findById(Mockito.anyString())).thenReturn(Optional.<AssetType>empty());
			assetTypeServiceImpl.getAssetTypeById(a.getId());
			assertEquals("e9b1df36-16c0-47ac-bc46-9e991f3959be",a.getId());


	}

	@Test
	public void getAllAssetTypesTest() throws ChemtreatException {
		List<AssetType> a = new ArrayList<>();
		a.add(AssetTypeTestUtil.getAssetTypeTemplate());
		when(assetTypeRepository.findAll()).thenReturn(a);
		// when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		List<AssetType> assetTypeList = assetTypeServiceImpl.getAllAssetTypes();
		assertNotEquals(0, assetTypeList.size());

	}

	@Test
	public void updateAssetTypeByIdTest() throws ChemtreatException {
		AssetType a = new AssetType();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetTypeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(a));
		when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		AssetTypeModelDTO am=new AssetTypeModelDTO();
		am.setName("xyz");
		String assetTypeId = assetTypeServiceImpl.updateAssetType(a.getId(), am);
		assertEquals("e9b1df36-16c0-47ac-bc46-9e991f3959be",assetTypeId);

	}

	@Test
	public void deleteAssetTypeByIdTest() throws ChemtreatException {
		AssetType a = new AssetType();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetTypeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(a));
		doNothing().when(assetTypeRepository).delete(Mockito.any(AssetType.class));
		assetTypeServiceImpl.deleteAssetType(a.getId());
		assertEquals("e9b1df36-16c0-47ac-bc46-9e991f3959be",a.getId());


	}

}
