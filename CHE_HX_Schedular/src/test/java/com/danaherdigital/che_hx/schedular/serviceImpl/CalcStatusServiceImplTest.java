package com.danaherdigital.che_hx.schedular.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.schedular.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.serviceimpl.CalcStatusServiceImpl;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CalcStatusServiceImplTest {

	@InjectMocks
	CalcStatusServiceImpl calcStatusServiceImpl;

	@Mock
	AssetDataLogRepository assetDataLogRepository;

	@Test
	public void getListUpTestSuccess() throws UnsupportedEncodingException {
		when(assetDataLogRepository.findByTenantId(ArgumentMatchers.any())).thenReturn(SchedularTestUtil.createMockAssetDataLogList());
		List<AssetDataLog> assetType_id = calcStatusServiceImpl.getList("2");
		assertEquals(false,assetType_id.isEmpty() );

	}

	@Test(expected = ResourceNotFoundException.class)
	public void getListUpTestFailed() throws UnsupportedEncodingException {
		List<AssetDataLog> list=new ArrayList<>();
		when(assetDataLogRepository.findByTenantId(ArgumentMatchers.any())).thenReturn(list);
		List<AssetDataLog> assetType_id = calcStatusServiceImpl.getList("null");
		assertEquals(0,assetType_id.size() );

	}

}
