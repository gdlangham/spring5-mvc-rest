package guru.springfamework.controller.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {
    public static final long ID = 3L;
    public static final String MY_VENDOR = "My vendor";
    @Mock
    VendorService vendorService;
    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void testListVendors() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        VendorDTO vendorDTO1 = new VendorDTO();
        List<VendorDTO> vendorDTOList = Arrays.asList(vendorDTO, vendorDTO1);

        when(vendorService.getAllVendors()).thenReturn(vendorDTOList);

        mockMvc.perform(get(VendorController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(MY_VENDOR);
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(getVendorUrl(ID))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(MY_VENDOR)));
    }

    @Test
    public void testCreateNewVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(MY_VENDOR);
        vendorDTO.setId(ID);
        when(vendorService.createNewVendor(any())).thenReturn(vendorDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(AbstractRestControllerTest.asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(MY_VENDOR)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(ID))));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(MY_VENDOR);
        vendorDTO.setVendorUrl(getVendorUrl(ID));

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(put(getVendorUrl(ID))
        .contentType(MediaType.APPLICATION_JSON)
        .content(AbstractRestControllerTest.asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(MY_VENDOR)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(ID))));
    }

    @Test
    public void testPatchVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(MY_VENDOR);
        vendorDTO.setId(ID);
        vendorDTO.setVendorUrl(getVendorUrl(ID));

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(patch(getVendorUrl(ID))
            .contentType(MediaType.APPLICATION_JSON)
            .content(AbstractRestControllerTest.asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(MY_VENDOR)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(ID))));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete(getVendorUrl(ID))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService, times(1)).deleteVendorById(anyLong());
    }
    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
