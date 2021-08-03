package guru.springfamework.service;

import guru.springfamework.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    public List<VendorDTO> getAllVendors();

    public VendorDTO getVendorById(Long id);

    public VendorDTO createNewVendor(VendorDTO vendorDTO);

    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    public void deleteVendorById(Long id);
}
