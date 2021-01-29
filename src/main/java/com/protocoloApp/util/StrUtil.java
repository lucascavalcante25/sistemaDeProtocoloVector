package com.protocoloApp.util;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.regex.Pattern;


public final class StrUtil {

	private static final Pattern pattrLimpaMascara = Pattern.compile("\\.|\\-|\\/|\\,|\\(|\\'|\\)");
	public static final String MSG_ERRO_EXCEPTION = "Ocorreu um erro não identificado. Contate o administrador do sistema para suporte.";
	public static final String MSG_ERRO_INCLUIR = "Ocorreu um erro ao tentar incluir o registro. Contate o administrador do sistema para suporte.";
	public static final String MSG_SUCESSO_INCLUIR = "Registro incluído com sucesso!";
	public static final String MSG_ERRO_ALTERAR = "Ocorreu um erro ao tentar atualizar o registro. Contate o administrador do sistema para suporte.";
	public static final String MSG_SUCESSO_ALTERAR = "Registro atualizado com sucesso!";
	public static final String MSG_ERRO_EXCLUIR = "Ocorreu um erro ao tentar excluir o registro. Contate o administrador do sistema para suporte.";
	public static final String MSG_SUCESSO_EXCLUIR = "Registro excluído com sucesso!";
	public static final String MSG_REGISTRO_CADASTRADO = "Registro já foi incluído anteriormente!";

	private StrUtil() {}
	
	public static String retiraMask(final String str) {
		synchronized (pattrLimpaMascara) {
			return pattrLimpaMascara.matcher(notNull(str)).replaceAll("");
		}
	}

	public static String rightTrim(String str) {
		int len = str.length();
		int st = 0;
		char[] val = str.toCharArray(); /* avoid getfield opcode */

		while ((st < len) && (val[len - 1] <= ' ')) {
			len--;
		}
		return ((st > 0) || (len < str.length())) ? str.substring(st, len) : str;
	}

	public static String leftTrim(String str) {
		int len = str.length();
		int st = 0;
		char[] val = str.toCharArray(); /* avoid getfield opcode */

		while ((st < len) && (val[st] <= ' ')) {
			st++;
		}
		return ((st > 0) || (len < str.length())) ? str.substring(st, len) : str;
	}

	public static String allTrim(String str) {
		return rightTrim(leftTrim(str));
	}

	public static boolean isEmpty(final String str) {
		return "".equals(notNull(str));
	}

	public static String notNull(final Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	public static String toUpper(String str) {
		return (str == null ? null : allTrim(str.toUpperCase()));
	}

	public static String toNull(String str) {
		return (str == null ? null : (str.equals("0") || str.length() == 0 ? null : allTrim(str)));
	}

	public static String quote(String str) {
		return (str == null ? null : "\"" + str + "\"");
	}

	public static String aspaSimples(String str) {
		return (str == null ? null : "\'" + str + "\'");
	}

	/**
	 * Preenche a esquerda com o caracter informado até o tamanho passado.
	 * 
	 * @param campo    String com o valor do campo
	 * @param tamanho  int com o tamanho de digitos que ele deverá preencher. Se for
	 *                 menor que a string, cortará à direita (reproduzindo o
	 *                 comportamento do Oracle)
	 * @param caracter char com o valor que ele deverá utilizar para preencher
	 * 
	 * @return temp String com o campo alterado
	 */
	public static String lpad(String campo, final int tamanho, final char caracter) {
		if (tamanho < 0) {
			return null;
		}
		if (campo == null) {
			campo = "";
		}
		final StringBuilder temp = new StringBuilder(tamanho);
		temp.append(campo);
		if (tamanho > campo.length()) {
			for (int i = 0; i < (tamanho - campo.length()); i++) {
				temp.insert(0, caracter);
			}
		} else {
			return temp.substring(0, tamanho);
		}
		return temp.toString();
	}

	/**
	 * Preenche a direita com o caracter informado até o tamanho passado
	 * 
	 * @param campo    String com o valor do campo
	 * @param tamanho  int com o tamanho de digitos que ele deverá preencher
	 * @param caracter char com o valor que ele deverá utilizar para preencher
	 * 
	 * @return temp String com o campo alterado
	 */
	public static String rpad(String campo, final int tamanho, final char caracter) {
		if (campo == null) {
			campo = "";
		}
		final StringBuilder temp = new StringBuilder(tamanho);
		temp.append(campo);
		if (tamanho > campo.length()) {
			for (int i = 0; i < (tamanho - campo.length()); i++) {
				temp.insert(campo.length(), caracter);
			}
		}
		return temp.toString();
	}

	public static String rpadTrunc(String campo, final int tamanho, final char caracter) {
		if (campo == null) {
			campo = "";
		}
		final StringBuilder temp = new StringBuilder(tamanho);
		temp.append(campo);
		if (tamanho > campo.length()) {
			for (int i = 0; i < (tamanho - campo.length()); i++) {
				temp.insert(campo.length(), caracter);
			}
		}
		return temp.toString().substring(0, tamanho);
	}

	public static String decimalToString(double number, int numDigsInt, int numDigsFrac) {
		NumberFormat df = DecimalFormat.getInstance();
		df.setMaximumFractionDigits(numDigsFrac);
		df.setMinimumFractionDigits(numDigsFrac);
		return StrUtil.lpad(df.format(number).replaceAll("[,|.]", ""), numDigsInt + numDigsFrac, '0');
	}

	public static String decimalToStringComPonto(double number, int numDigsInt, int numDigsFrac) {
		NumberFormat df = DecimalFormat.getInstance();
		df.setMaximumFractionDigits(numDigsFrac + 1);
		df.setMinimumFractionDigits(numDigsFrac);
		return StrUtil.lpad(df.format(number).replaceAll("[,|.]", "."), numDigsInt + 1 + numDigsFrac, '0');
	}

	public static String dataAtual() {
		String str = "";

		GregorianCalendar c = new GregorianCalendar();

		str = c.get(Calendar.YEAR) + "" + StrUtil.lpad(String.valueOf(c.get(Calendar.MONTH) + 1), 2, '0') + "" + StrUtil.lpad(String.valueOf(c.get(Calendar.DAY_OF_MONTH)), 2, '0');

		return str;
	}

	/**
	 * Retorna uma data no formato dd/MM/yyyy
	 * 
	 * @param data
	 * @return
	 */
	public static String dataFormatada(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}

	/**
	 * Retorna uma data no formato dd/MM/yyyy hh:mm:ss
	 * 
	 * @param data
	 * @return
	 */
	public static String dataHoraFormatada(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}

	/**
	 * Retorna uma data no formato hh:mm:ss
	 * 
	 * @param data
	 * @return
	 */
	public static String horaFormatada(Date data) {
		return new SimpleDateFormat("HH:mm:ss").format(data);
	}

	/**
	 * Retorna o nome do m�s por extenso
	 * 
	 * @param mes
	 * @return
	 */
	public static String mesPorExtenso(Integer mes) {

		String[] meses = { "Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };

		return meses[mes - 1];
	}

	/**
	 * Retorna o nome do m�s abreviado
	 * 
	 * @param mes
	 * @return
	 */
	public static String mesAbreviadoPorExtenso(Integer mes) {

		return mesPorExtenso(mes).substring(0, 3);
	}

	/**
	 * Remove os acentos de uma String
	 * 
	 * @param str
	 * @return
	 */
	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Retorna uma data com hora zerada para ser utilizada em consultas com between
	 * 
	 * @param dataInicial
	 * @return
	 */
	public static Date inicioPeriodo(Date dataInicial) {
		Calendar inicioPeriodo = Calendar.getInstance();
		inicioPeriodo.setTime(dataInicial);
		inicioPeriodo.set(Calendar.HOUR_OF_DAY, 0);
		inicioPeriodo.set(Calendar.MINUTE, 0);
		inicioPeriodo.set(Calendar.SECOND, 0);
		return inicioPeriodo.getTime();
	}

	/**
	 * Retorna uma data com hora 23:59:59 para ser utilizada em consultas com
	 * between
	 * 
	 * @param dataFinal
	 * @return
	 */
	public static Date fimPeriodo(Date dataFinal) {
		Calendar fimPeriodo = Calendar.getInstance();
		fimPeriodo.setTime(dataFinal);
		fimPeriodo.set(Calendar.HOUR_OF_DAY, 23);
		fimPeriodo.set(Calendar.MINUTE, 59);
		fimPeriodo.set(Calendar.SECOND, 59);
		return fimPeriodo.getTime();
	}

	public static String anoData(Date data) {
		return new SimpleDateFormat("yyyy").format(data);
	}

	public static Date stringDataHoraParaDataHora(String dataHora) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formato.parse(dataHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date stringDataHoraParaDataHoraSegundo(String dataHora) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return formato.parse(dataHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converte uma String para o tipo Date no formato dd/MM/yyyy
	 * 
	 * @param data
	 * @return
	 */
	public static Date stringDataParaDate(String data) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			return formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna uma formatada para o padr�o MM/yyyy
	 * 
	 * @param data
	 * @return MM/yyyy
	 */
	public static String dataParaMesAno(Date data) {
		SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
		return formato.format(data);
	}

	/**
	 * Retorna uma data acrescentada por x meses
	 * 
	 * @param data, mes
	 * @return MM/yyyy
	 */

	public static Date adicionarMesData(Date data, Integer mes) {
		Calendar meses = Calendar.getInstance();
		meses.setTime(data);
		meses.add(Calendar.MONTH, mes);
		return meses.getTime();
	}

	/**
	 * Adiciona uma quantidade x de dias � uma data.
	 * 
	 * @param data
	 * @param dias
	 * @return data adicionada de dias.
	 */
	public static Date adicionarDiasData(Date data, Integer dias) {
		Calendar fimPeriodo = Calendar.getInstance();
		fimPeriodo.setTime(data);
		fimPeriodo.set(Calendar.HOUR_OF_DAY, 0);
		fimPeriodo.set(Calendar.MINUTE, 0);
		fimPeriodo.set(Calendar.SECOND, 0);
		fimPeriodo.add(Calendar.DAY_OF_MONTH, dias);
		return fimPeriodo.getTime();
	}

	public static Object dataPorExtenso(Date data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd ' de ' MMMM ' de ' yyyy");
		return formato.format(data);
	}

	/**
	 * Converte coordenadas de graus para decimal
	 * 
	 * @param graus
	 * @return
	 */
	public static String grausParaDecimal(String graus) {

		graus = graus.replace("''", "\"");
		Integer g = 0;
		Integer m = 0;
		Integer s = 0;
		g = Integer.parseInt(graus.substring(0, 1));
		m = Integer.parseInt(graus.substring(4, 5));
		s = Integer.parseInt(graus.substring(7, 8));
		Double dd = Math.signum(g) * (Math.abs(g) + (m / 60.0) + (s / 3600.0));

		return dd.toString();
	}

	/**
	 * Converte coordenadas de decimal para graus
	 * 
	 * @param decimal
	 * @return
	 */
	public static String decimalParaGraus(String decimal) {
		String graus = "";
		// TODO Fazer conversor

		return graus;
	}

	public static boolean cpfValido(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		// if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		// CPF.equals("22222222222")
		// || CPF.equals("33333333333") || CPF.equals("44444444444") ||
		// CPF.equals("55555555555")
		// || CPF.equals("66666666666") || CPF.equals("77777777777") ||
		// CPF.equals("88888888888")
		// || CPF.equals("99999999999") || (CPF.length() != 11))
		// return (false);

		if (CPF != null) {

			char dig10, dig11;
			int sm, i, r, num, peso;

			try {
				// Calculo do 1o. Digito Verificador
				sm = 0;
				peso = 10;
				for (i = 0; i < 9; i++) {
					// converte o i-esimo caractere do CPF em um numero:
					// por exemplo, transforma o caractere '0' no inteiro 0
					// (48 eh a posicao de '0' na tabela ASCII)
					num = (int) (CPF.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso - 1;
				}

				r = 11 - (sm % 11);
				if ((r == 10) || (r == 11))
					dig10 = '0';
				else
					dig10 = (char) (r + 48); // converte no respectivo caractere numerico

				// Calculo do 2o. Digito Verificador
				sm = 0;
				peso = 11;
				for (i = 0; i < 10; i++) {
					num = (int) (CPF.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso - 1;
				}

				r = 11 - (sm % 11);
				if ((r == 10) || (r == 11))
					dig11 = '0';
				else
					dig11 = (char) (r + 48);

				// Verifica se os digitos calculados conferem com os digitos informados.
				if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
					return (true);
				else
					return (false);
			} catch (InputMismatchException erro) {
				return (false);
			}
		}
		return false;
	}

	/**
	 * Retorna apenas os números do cpf
	 * 
	 * @param cpf
	 * @return
	 */
	public static String apenasNumeroCpf(String cpf) {
		if (cpf != null && !cpf.isEmpty()) {
			cpf = cpf.toString().replaceAll("[- /.]", "");
			cpf = cpf.toString().replaceAll("[-()]", "");
		}
		return cpf;
	}

	/**
	 * Retorna um CPF com máscara
	 * 
	 * @param cpf
	 * @return
	 */
	public static String cpfComMascara(String cpf) {
		if (cpf != null && !cpf.isEmpty()) {
			String aux = cpf;
			cpf = aux.substring(0, 3) + "." + aux.substring(3, 6) + "." + aux.substring(6, 9) + "-" + aux.substring(9, 11);
		}
		return cpf;
	}

	public static boolean cnpjValido(String CNPJ) {
		if (CNPJ != null && !CNPJ.isEmpty()) {

			// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
			if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444")
					|| CNPJ.equals("55555555555555") || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
					|| (CNPJ.length() != 14))
				return (true);

			char dig13, dig14;
			int sm, i, r, num, peso;

			try {
				// Calculo do 1o. Digito Verificador
				sm = 0;
				peso = 2;
				for (i = 11; i >= 0; i--) {
					// converte o i-ésimo caractere do CNPJ em um número:
					// por exemplo, transforma o caractere '0' no inteiro 0
					// (48 eh a posição de '0' na tabela ASCII)
					num = (int) (CNPJ.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso + 1;
					if (peso == 10)
						peso = 2;
				}

				r = sm % 11;
				if ((r == 0) || (r == 1))
					dig13 = '0';
				else
					dig13 = (char) ((11 - r) + 48);

				// Calculo do 2o. Digito Verificador
				sm = 0;
				peso = 2;
				for (i = 12; i >= 0; i--) {
					num = (int) (CNPJ.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso + 1;
					if (peso == 10)
						peso = 2;
				}

				r = sm % 11;
				if ((r == 0) || (r == 1))
					dig14 = '0';
				else
					dig14 = (char) ((11 - r) + 48);

				// Verifica se os dígitos calculados conferem com os dígitos informados.
				if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
					return (true);
				else
					return (false);
			} catch (InputMismatchException erro) {
				return (false);
			}
		}
		return false;
	}

	/**
	 * Retorna apenas os números do CNPJ
	 * 
	 * @param cnpj
	 * @return
	 */
	public static String cnpjSemMascara(String cnpj) {
		if (cnpj != null && !cnpj.isEmpty()) {
			cnpj = cnpj.toString().replaceAll("[- /.]", "");
			cnpj = cnpj.toString().replaceAll("[-()]", "");
		}
		return cnpj;
	}

	public static String cnpjComMascara(String cnpj) {
		if (cnpj != null && !cnpj.isEmpty()) {
			String aux = cnpj;
			cnpj = aux.substring(0, 2) + "." + aux.substring(2, 5) + "." + aux.substring(5, 8) + "/" + aux.substring(8, 12) + "-" + aux.substring(12, 14);
		}
		return cnpj;
	}

}
