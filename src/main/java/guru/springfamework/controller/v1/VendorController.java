package guru.springfamework.controller.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(final VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO listAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        VendorDTO vendorDTO = vendorService.getVendorById(id);
        if (vendorDTO != null) {
            vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + id);
        }
        return vendorDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);
        savedVendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + savedVendorDTO.getId());
        return savedVendorDTO;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }
}
