/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laundry.laundry;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author victus
 */
@RestController
@RequestMapping("/order.html")
public class MyController {
    
    PembeliJpaController pbl = new PembeliJpaController();
    
    @RequestMapping("/read")
    public String getData(){
    Pembeli buffer = new Pembeli();
    buffer.setNama(pbl.findPembeli(1).getNama());
    return buffer.getNama();
    }
    @RequestMapping("/readAll")
    public List<Pembeli> getAll() {
        List<Pembeli> allData = pbl.findPembeliEntities();
        return allData;
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createData(@RequestBody Pembeli newPembeli) {
        try {
            pbl.create(newPembeli);
            return "Create Data Succesfully";
        } catch (Exception e) {
            return "Failed to create data" + e.getMessage();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateData(@PathVariable("id") int id, @RequestBody Pembeli updatedData) {
        try {
            // Get existing data by ID
            Pembeli existingData = pbl.findPembeli(id);

            // Update the fields
            existingData.setNama(updatedData.getNama());
            existingData.setAlamat(updatedData.getAlamat());
            existingData.setBerat(updatedData.getHarga());

            // Save the updated data
            pbl.edit(existingData);

            return "Update Data Successfully";
        } catch (Exception e) {
            return "Failed to update data" + e.getMessage();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteData(@PathVariable("id") int id) {
        try {
            pbl.destroy(id);
            return "Delete data successfully";
        } catch (Exception e) {
            return "Failed to delete data" + e.getMessage();
        }
    }  
}
