package isutc.pfc.bean;

import java.io.InputStream;

public class Document
{
	public String title;
	public String description;
	private String format;
	public int id;
	private byte[] text;//o texto puro
	private InputStream textStream;  //texto puro como stream (para upload de dados grandes)
	
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public InputStream getTextStream() {
		return textStream;
	}
	public void setTextStream(InputStream textStream) {
		this.textStream = textStream;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String _format) {
		this.format = _format;
	}
	public byte[] getText() {
		return text;
	}
	public void setText(byte[] text) {
		this.text = text;
	}
	
}