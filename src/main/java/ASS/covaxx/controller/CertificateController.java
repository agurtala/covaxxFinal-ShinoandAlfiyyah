// ************************** Alfiyyah's Contribution **********************/

package ASS.covaxx.controller;

import ASS.covaxx.model.Certificate;
import ASS.covaxx.repo.PatientRepo;
import ASS.covaxx.repo.CertificateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
@CrossOrigin
public class CertificateController {

    @Autowired
    private CertificateRepo Certificate;

    @Autowired
    private PatientRepo Patient;

    @GetMapping("/certificate")
    private @ResponseBody
    Collection<Certificate> getAll() {
        return this.Certificate.getAll();
    }

//    ) {
//        Patient patient = this.Patient.getById(patientID);
//
//        if (patient == null)
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no patient with this patientID");
//
//        return Certificate.find(patientID, null);
//    }


    @GetMapping("/certificate/{certID}")
    public @ResponseBody
    Certificate getOne(
            @PathVariable String certID)
    {

        Certificate certificate = this.Certificate.getById(certID);

        if (certificate == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no certificate with this certID");

        return certificate;
    }

    @PatchMapping("/certificate/{certID}")
    public @ResponseBody
    Certificate updateExisting(@PathVariable String certID, @RequestBody Certificate changes) {

        Certificate existingCertificate = this.Certificate.getById(certID);

        if (existingCertificate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This certID does not exist");
        }

        if (changes.files != null) {
            existingCertificate.files = changes.files;
        }

        if (changes.certDate != null) {
            existingCertificate.certDate = changes.certDate;
        }

        if (changes.certTime != null) {
            existingCertificate.certTime = changes.certTime;
        }
        if (changes.vacResult != null) {
            existingCertificate.vacResult = changes.vacResult;
        }
        if (changes.facility != null) {
            existingCertificate.facility = changes.facility;
        }
        if (changes.summary != null) {
            existingCertificate.summary = changes.summary;
        }
        if (changes.patientID != null) {
            existingCertificate.patientID = changes.patientID;
        }

        this.Certificate.save(existingCertificate);

        return existingCertificate;


    }

    @PostMapping("/certificate")
    private @ResponseBody
    Certificate createNew(
//            @PathVariable String patientID,
            @RequestBody Certificate certificate

    ) {

        if (certificate.certID != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New certificate should not specify any ID");

////        certificate.patientID = patientID;
//
        validate(certificate);

        this.Certificate.save(certificate);
        return certificate;


    }


    private void validate(Certificate certificate) {
        if (certificate.patientID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No patient ID specified");

        }
        if (Patient.getById(certificate.patientID) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no patient with this ID");


        }
    }
}

// ************************** Alfiyyah's Contribution **********************/




