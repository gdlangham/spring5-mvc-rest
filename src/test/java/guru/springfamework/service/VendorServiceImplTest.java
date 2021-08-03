package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controller.v1.CustomerController;
import guru.springfamework.controller.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    public static final String VENDOR_NAME = "Electric Supply";
    public static final Long VENDOR_ID = 1L;
    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() {
        List<Vendor> vendorList = Arrays.asList(new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendorList);

        List<VendorDTO> returnedVendors = vendorService.getAllVendors();

        assertEquals(1, returnedVendors.size());
    }

    @Test
    public void getVendorById() {
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR_NAME);
        vendor.setId(VENDOR_ID);
        Optional<Vendor> vendorOptional = Optional.of(vendor);

        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

        VendorDTO vendorDTO = vendorService.getVendorById(VENDOR_ID);
        assertNotNull(vendorDTO);
    }

    @Test
    public void testCreateNewVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR_NAME);
        vendor.setId(5L);
        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(5L);
        vendorDTO.setName(VENDOR_NAME);
        VendorDTO returnedVendorDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(Long.valueOf(5), returnedVendorDTO.getId());
        assertEquals(VENDOR_NAME, returnedVendorDTO.getName());
        assertEquals(getVendorUrl(returnedVendorDTO.getId()), returnedVendorDTO.getVendorUrl());
    }

    @Test
    public void testSaveVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR_NAME);
        vendor.setId(VENDOR_ID);
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(vendor.getId());
        vendorDTO.setName(vendor.getName());

        VendorDTO returnedVendorDTO = vendorService.saveVendorByDTO(VENDOR_ID, vendorDTO);

        assertEquals(1, returnedVendorDTO.getId().longValue());
        assertEquals(vendor.getName(), returnedVendorDTO.getName());
        assertEquals(getVendorUrl(vendorDTO.getId()), returnedVendorDTO.getVendorUrl());
    }

    @Test
    public void testPatchVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR_NAME);
        vendor.setId(VENDOR_ID);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(VENDOR_ID);
        vendorDTO.setName(VENDOR_NAME);
        vendorDTO.setVendorUrl(getVendorUrl(VENDOR_ID));

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO returnedVendor = vendorService.patchVendor(VENDOR_ID, vendorDTO);

        assertEquals(VENDOR_ID, returnedVendor.getId());
        assertEquals(VENDOR_NAME, returnedVendor.getName());
        assertEquals(getVendorUrl(VENDOR_ID), returnedVendor.getVendorUrl());
    }

    @Test
    public void testDeleteVendor() throws Exception {

        Vendor vendor = new Vendor();
        vendor.setId(VENDOR_ID);
        vendor.setName(VENDOR_NAME);
        Optional<Vendor> vendorOptional = Optional.of(vendor);

        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

        vendorService.deleteVendorById(vendor.getId());

        verify(vendorRepository, times(1)).delete(any(Vendor.class));
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}