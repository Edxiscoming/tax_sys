package com.edison.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {

	@Test
	public void testWrite03Excel() throws Exception {
		//1������/��ȡ������
		HSSFWorkbook workbook=new HSSFWorkbook();
		//2������/��ȡ������
		HSSFSheet sheet=workbook.createSheet("hello,world");
		//3������/��ȡ��
		HSSFRow row=sheet.createRow(3);
		//4������/��ȡ��Ԫ��
		HSSFCell cell=row.createCell(3);
		cell.setCellValue("hello,edison-xu");
		
		//���ڴ������Ӳ��
		FileOutputStream outputStream=new FileOutputStream("F:\\����.xls");
		workbook.write(outputStream);//���������ĵط�
		outputStream.close();
		workbook.close();
	}
	
	@Test
	public void testRead03Excel() throws Exception {
		FileInputStream inputStream=new FileInputStream("F:\\����.xls");
		//1����ȡ������
		HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
		//2����ȡ������
		HSSFSheet sheet=workbook.getSheetAt(0);
		//3����ȡ��
		HSSFRow row=sheet.getRow(3);
		//4������/��ȡ��Ԫ��
		HSSFCell cell=row.getCell(3);
		String result=cell.getStringCellValue();
		System.out.println(result);
		
		inputStream.close();
		workbook.close();
	}

	@Test
	public void testRead03And07Excel() throws Exception {
		String fileName = "D:\\itcast\\����.xlsx";
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//�ж��Ƿ�excel�ĵ�
			
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			
			FileInputStream inputStream = new FileInputStream(fileName);
			
			//1����ȡ������
			Workbook workbook = is03Excel ?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			//2����ȡ��һ��������
			Sheet sheet = workbook.getSheetAt(0);
			//3����ȡ�У���ȡ��3��
			Row row = sheet.getRow(2);
			//4����ȡ��Ԫ�񣻶�ȡ��3�е�3��
			Cell cell = row.getCell(2);
			System.out.println("��3�е�3�е�Ԫ�������Ϊ��" + cell.getStringCellValue());
			
			workbook.close();
			inputStream.close();
		}
	}
	
	
	@Test
	public void testExcelStyle() throws Exception {
		//1������������
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1�������ϲ���Ԫ�����;�ϲ���3�еĵ�3�е���5��
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);//��ʼ�кţ������кţ���ʼ�кţ������к�
		//1.2��������Ԫ����ʽ
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����
		//1.3����������
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//�Ӵ�����
		font.setFontHeightInPoints((short) 16);//���������С
		//��������
		style.setFont(font);
		
		//��Ԫ�񱳾�
		//���ñ������ģʽ
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//������䱳��ɫ
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		//�������ǰ��ɫ
		style.setFillForegroundColor(HSSFColor.RED.index);
		
		//2������������
		HSSFSheet sheet = workbook.createSheet("Hello World");//ָ����������
		//2.1�����غϲ���Ԫ�����
		sheet.addMergedRegion(cellRangeAddress);
		
		//3�������У�������3��
		HSSFRow row = sheet.createRow(2);
		//4��������Ԫ�񣻴�����3�е�3��
		HSSFCell cell = row.createCell(2);
		//������ʽ
		cell.setCellStyle(style);
		cell.setCellValue("Hello World!");
		
		//�����Ӳ��
		FileOutputStream outputStream = new FileOutputStream("F:\\����.xls");
		//��excel���������ĵ�ַ
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
