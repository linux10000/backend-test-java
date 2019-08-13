package br.com.fcamara.provajava.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fcamara.provajava.dao.MovVeiculoDao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Lazy(true)
    @Autowired
    ApplicationContext context;
	@Lazy(true)
	@Autowired
	private MovVeiculoDao movVeiculoDao;
	
	@GetMapping(path = "/sumario/pdf")
	public @ResponseBody ResponseEntity<byte[]> getSumarioPdf() throws JRException, IOException {
      Resource resource = context.getResource("classpath:reports/sumario.jrxml");

      InputStream inputStream = resource.getInputStream();
      JasperReport report = JasperCompileManager.compileReport(inputStream);

      Map<String, Object> params = new HashMap<>();

      List<Map<String, Object>> movs = movVeiculoDao.getGeneralSummary();
      
      JRDataSource dataSource = new JRBeanCollectionDataSource(movs);
      params.put("datasource", dataSource);

      JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
      
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, bos);
      
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.setContentType(MediaType.APPLICATION_PDF);

      return ResponseEntity.ok().headers(responseHeaders).body(bos.toByteArray());
	}

	@GetMapping(path = "/sumario-por-hora/pdf")
	public @ResponseBody ResponseEntity<byte[]> getSumarioPorHoraPdf() throws JRException, IOException {
      Resource resource = context.getResource("classpath:reports/sumario-por-hora.jrxml");

      InputStream inputStream = resource.getInputStream();
      JasperReport report = JasperCompileManager.compileReport(inputStream);

      Map<String, Object> params = new HashMap<>();

      List<Map<String, Object>> movs = movVeiculoDao.getSummaryByHour();
      
      JRDataSource dataSource = new JRBeanCollectionDataSource(movs);
      params.put("datasource", dataSource);

      JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
      
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, bos);
      
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.setContentType(MediaType.APPLICATION_PDF);

      return ResponseEntity.ok().headers(responseHeaders).body(bos.toByteArray());
	}
}
