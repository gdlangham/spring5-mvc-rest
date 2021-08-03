package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void testVendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Home Depot");

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getId(), vendorDTO.getId());
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void testVendorDTOToVendor() {
        VendorDTO vendorDTO= new VendorDTO();
        vendorDTO.setName("Sheila");
        vendorDTO.setId(3L);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        assertEquals("Sheila", vendor.getName());
        assertEquals("3", vendor.getId().toString());
    }
}