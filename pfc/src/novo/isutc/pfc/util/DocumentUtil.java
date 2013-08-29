package novo.isutc.pfc.util;

import isutc.pfc.bean.Pfc;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DocumentUtil {
	/**
	 * Responsavel por comprimir os dados do pdf para que possam ser armazenados na BD
	 * @param dadosBrutos os dados do document pdf
	 * @param format
	 * @return
	 */
	public byte[] compressDocument(FileInputStream dadosBrutos,String formato)
	{
		ByteArrayOutputStream compressedData = new ByteArrayOutputStream();
		try
		{
			GZIPOutputStream compressor = new GZIPOutputStream(compressedData);
			int read = -1;
			while((read = dadosBrutos.read()) != -1)
            {
                compressor.write(read);
            }
			dadosBrutos.close();

			          
			compressor.close();
		}
		catch(IOException convertX)
		{
			throw new RuntimeException(formato+ "GZIP" + convertX);
		}
		return compressedData.toByteArray(); 
	}

	/**
	 * Responsavel por descomprir os dados da bd para dados que possam ser convertidos no ficheiro pdf.
	 * Deve ser usado somente quando for a disponibilizar o pdf.
	 * @param compresseData
	 * @return
	 */
	public byte[] decompressDocument(byte[] compresseData,String formato)
	{
		ByteArrayOutputStream realData = new ByteArrayOutputStream(); 
        ByteArrayInputStream compressedData = new ByteArrayInputStream(compresseData);
		Pfc pfc = new Pfc();
		
		try
        {
			InputStream decompressor = new BufferedInputStream(new GZIPInputStream(compressedData));
			
			int read = -1;
			while((read = decompressor.read()) != -1)
			{
				realData.write(read);
			}
			return realData.toByteArray();
        }catch(IOException convertX)
		{
			throw new RuntimeException("GZIP " + formato, convertX);
		}	
	}
}
