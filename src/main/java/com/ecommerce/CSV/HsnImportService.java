package com.ecommerce.CSV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ecommerce.Entity.HsnMaster;
import com.ecommerce.Repository.HsnMasterRepository;

@Service
public class HsnImportService {

    private final HsnMasterRepository repo;

    public HsnImportService(HsnMasterRepository repo) {
        this.repo = repo;
    }

    public void importHsnData() throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("GST.csv").getInputStream()
                )
        );

        String line;

        // Skip CSV header
        br.readLine();

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", 2);

            if (data.length < 2) {
                continue;
            }

            String hsnCode = data[0].trim();
            String description = data[1].trim();

            double gstRate = getGstRate(hsnCode);

            HsnMaster hsn = new HsnMaster();
            hsn.setHsn_code(hsnCode);
            hsn.setDescription(description);
            hsn.setGst_rate(gstRate);

            repo.save(hsn);
        }

        br.close();
    }

    // GST Mapping Logic
    private double getGstRate(String hsnCode) {

        String chapter = hsnCode.substring(0, 2);

        switch (chapter) {

            case "01":
            case "02":
            case "03":
            case "04":
            case "05":
                return 5;

            case "50":
            case "51":
            case "52":
            case "53":
            case "54":
            case "55":
            case "56":
            case "57":
            case "58":
            case "59":
            case "60":
            case "61":
            case "62":
            case "63":
                return 12;

            case "21":
                return 28;

            default:
                return 18;
        }
    }

    public List<HsnMaster> getAllHsn() {
        return repo.findAll();
    }
}