package net.hdcx.view.main.menu;

import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 导出对话框
 * Created by Kevin on 2017/3/4.
 */
public class Exporter {
	private Component parentComponent = null;
	private JFileChooser chooser = null;
	private String filePath  = null;

	public Exporter(Component parentComponent){
		this.parentComponent = parentComponent;
		this.openFileDialog();
		try {
			this.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openFileDialog(){
		//设置文件扩展名
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
		chooser = new JFileChooser("./");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setDialogTitle("选择Excel文件路径");
		int result = chooser.showSaveDialog(parentComponent);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			filePath = file.getAbsolutePath();
		}else if(result == JFileChooser.ERROR_OPTION){
			JOptionPane.showMessageDialog(chooser, "选择文件路径失败");
		}
	}

	public void createMemberColumnName(Workbook wb, Sheet memberSheet){
		Row memberColumnName = memberSheet.createRow(0);
		String titles[] = {"学号","姓名","联系电话","邮箱","所在部门","值班周期","班次","签到次数","签退次数","迟到次数","请假次数","旷班次数"};
		Cell[] memberTitles = new Cell[titles.length];
		int length = titles.length;
		for(int i = 0;i<length;i++){
			memberTitles[i] = memberColumnName.createCell(i, CellType.STRING);
			CellStyle style = this.getHeaderStyle(wb);
			memberTitles[i].setCellStyle(style);
			memberTitles[i].setCellValue(titles[i]);
		}
	}

	public void createMinisterColumnName(Workbook wb, Sheet ministerSheet){
		Row ministerColumnName = ministerSheet.createRow(0);
		String titles[] = {"学号","姓名","联系电话","邮箱","所在部门","值班周期","班次","签到次数","签退次数","迟到次数","请假次数","旷班次数"};
		Cell[] memberTitles = new Cell[titles.length];
		int length = titles.length;
		for(int i = 0;i<length;i++){
			memberTitles[i] = ministerColumnName.createCell(i, CellType.STRING);
			CellStyle style = this.getHeaderStyle(wb);
			memberTitles[i].setCellStyle(style);
			memberTitles[i].setCellValue(titles[i]);
		}
	}

	public CellStyle getHeaderStyle(Workbook wb){
		CellStyle style = wb.createCellStyle();
		Font headerFont = wb.createFont();
		//设置对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		//设置字体
		headerFont.setFontName("microsoft Yahei");
		headerFont.setFontHeightInPoints((short)16);

		style.setFont(headerFont);
		return style;
	}

	public CellStyle getContentStyle(Workbook wb){
		CellStyle style = wb.createCellStyle();
		Font headerFont = wb.createFont();
		//设置对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		//设置字体
		headerFont.setFontName("宋体");
		headerFont.setFontHeightInPoints((short)14);

		style.setFont(headerFont);
		return style;
	}

	public String getFilePath(){
		return filePath;
	}

	public int getMembersInfo(Workbook wb, Sheet memberSheet) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from members";
		List<Object[]> membersTable = qr.query(sql, new ArrayListHandler());
		for (int i = 0, size = membersTable.size(); i < size; i++){
			Row row = memberSheet.createRow(i+1);
			for (int j = 0, length = membersTable.get(i).length; j < length; j++){
				CellStyle style = getContentStyle(wb);
				Cell cell = row.createCell(j);
				cell.setCellStyle(style);
				cell.setCellValue(membersTable.get(i)[j].toString());
			}
		}
		return membersTable.get(0).length;
	}

	public int getMinisterInfo(Workbook wb, Sheet ministerSheet) throws SQLException{
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from ministers";
		List<Object[]> ministersTable = qr.query(sql, new ArrayListHandler());
		for (int i = 0, size = ministersTable.size(); i < size; i++){
			Row row = ministerSheet.createRow(i+1);
			for (int j = 0, length = ministersTable.get(i).length; j < length; j++){
				CellStyle style = getContentStyle(wb);
				Cell cell = row.createCell(j);
				cell.setCellStyle(style);
				cell.setCellValue(ministersTable.get(i)[j].toString());
			}
		}
		return ministersTable.get(0).length;
	}

	public void save() throws IOException, SQLException{
		Workbook wb = new XSSFWorkbook();
		Sheet memberSheet = wb.createSheet("干事信息表");
		Sheet ministerSheet = wb.createSheet("部长信息表");
		int columns = 0;
		if(this.getFilePath() != null){
			this.createMemberColumnName(wb, memberSheet);
			columns = this.getMembersInfo(wb, memberSheet);
			//调整单元格大小
			for(int i = 0; i < columns; i++){
				memberSheet.autoSizeColumn(i);
			}

			this.createMinisterColumnName(wb, ministerSheet);
			columns = this.getMinisterInfo(wb, ministerSheet);
			//调整单元格大小
			for(int i = 0; i < columns; i++){
				ministerSheet.autoSizeColumn(i);
			}

			FileOutputStream fos = null;
			try {
				if(this.getFilePath().contains(".xlsx")){
					fos = new FileOutputStream(new File(this.getFilePath()));
				}else{
					fos = new FileOutputStream(new File(this.getFilePath()+".xlsx"));
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(chooser, "保存文件失败");;
				e.printStackTrace();
			}

			wb.write(fos);
			fos.flush();
			fos.close();
		}
	}
}
