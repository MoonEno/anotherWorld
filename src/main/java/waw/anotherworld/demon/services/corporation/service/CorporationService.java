package waw.anotherworld.demon.services.corporation.service;


import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import waw.anotherworld.demon.common.constants.ServiceConstant;
import waw.anotherworld.demon.common.http.HttpCallService;
import waw.anotherworld.demon.common.http.HttpResultVO;
import waw.anotherworld.demon.common.mail.MailService;
import waw.anotherworld.demon.common.util.UtilDate;
import waw.anotherworld.demon.services.corporation.mapper.CorporationDao;
import waw.anotherworld.demon.services.corporation.model.CorporationVO;
import waw.anotherworld.demon.services.disclosure.mapper.DisClosureDao;
import waw.anotherworld.demon.services.disclosure.model.DisClosureResultVO;
import waw.anotherworld.demon.services.disclosure.model.DisClosureVO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CorporationService {


    @Autowired
    CorporationDao corporationDao;


    public void insertCorpList() {

        FileInputStream file = null;
        List<CorporationVO> list = new ArrayList<>();

        try {
            file = new FileInputStream("C:\\log\\corplist.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();

            for( int rowIdx = 0; rowIdx<rows; rowIdx++) {
                XSSFRow row = sheet.getRow(rowIdx);

                if (row != null) {
                    CorporationVO corp = new CorporationVO();
                    int cells = row.getPhysicalNumberOfCells();
                    for ( int colIdx = 0; colIdx < cells; colIdx++ ) {

                        XSSFCell cell = row.getCell(colIdx);
                        String value = "";
                        if (cell == null) {
                            continue;
                        } else {
                            switch (cell.getCellType()) {
                                case STRING :
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date) + "";
                                    } else {
                                        value =  cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case FORMULA:
                                    value = cell.getCellFormula() + "";
                                    break;
                                case BLANK:
                                    continue;
                                case ERROR:
                                    value = cell.getErrorCellValue() + "";
                            }

                        }

                        // 해당 열에 맞는 VO에 값 넣기
                        switch (colIdx) {
                            case 0 : corp.setCorpName(value); break;
                            case 1 : corp.setCorpCode(value); break;
                            case 2 : corp.setCorpCategory(value); break;
                            case 3 : corp.setCorpProduct(value); break;
                            case 4 : corp.setCorpDay(value); break;
                            case 5 : corp.setCorpHomepage(value); break;
                        }
                    }

                    list.add(corp);
                }
            }

            if (list.size() > 0) {
                int result = corporationDao.insertCorpList(list);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
