package com.hj.tj.gohome.json;

import com.hj.tj.gohome.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 对应数据协议中的的sdk参数封装类。
 * <p/>
 */
public class Client implements Cloneable {
	public static final String DEFAULT_MAC = "NONE";
	public static final String DEFAULT_IMEI = "-065535";
	public static final String DEFAULT_GAID = "";

	private static final Logger log = LoggerFactory.getLogger(Client.class);
	// ------------------------------ FIELDS ------------------------------

	private String sn = "";

	private String imei = DEFAULT_IMEI;

	private String udid = "";

	private String imsi = "";

	private String mac = DEFAULT_MAC;

	private String model = "";

	private String fw = "";

	private String ua = "";

	private String isp = "";

	private String nt = "";

	private String nw = "";

	private String os = "";

	private String ver = "";

	private int vc = 0;

	private String ch = "";

	private String province = "";

	private String city = "";

	private String lng = "";

	private String lat = "";

	private String pf = "";

	private String ca = "";

	private String ostype = "";

	private String ip = "";

	private String adSource;

	private String pkg;

	private String area;

	// 客户端上报时间戳
	private long time;

	private String gaid;

	// cpu架构
	private String platform;

	// 语言
	private String lang;

	// 请求来源的标识 参考 RequestSourceEnum
	private String source;

	public String getGaid() {
		if (StringUtils.isEmpty(this.gaid) || StringUtil.isInvalidImei(this.gaid)) {
			log.warn("gaid_is_blank_or_invalid={}", this.gaid);
			return DEFAULT_GAID;
		}
		return gaid;
	}

	public void setGaid(String gaid) {
		this.gaid = gaid;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getImei() {
		if (StringUtils.isEmpty(this.imei) || StringUtil.isInvalidImei(this.imei)) {
			log.warn("imei_is_blank_or_invalid={}", this.imei);
			return DEFAULT_IMEI;
		}
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFw() {
		return fw;
	}

	public void setFw(String fw) {
		this.fw = fw;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getNt() {
		return nt;
	}

	public void setNt(String nt) {
		this.nt = nt;
	}

	public String getNw() {
		return nw;
	}

	public void setNw(String nw) {
		this.nw = nw;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getVc() {
		return vc;
	}

	public void setVc(int vc) {
		this.vc = vc;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public static Logger getLog() {
		return log;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public static boolean isImeiEmpty(String imei) {
		if (StringUtils.isEmpty(imei)) {
			return true;
		}
		if (imei.equals(DEFAULT_IMEI)) {
			return true;
		}
		return false;
	}

	public static boolean isGaidEmpty(String gaid) {
		if (StringUtils.isEmpty(gaid)) {
			return true;
		}
		if (gaid.equals(DEFAULT_GAID)) {
			return true;
		}
		return false;
	}

	public static boolean isMacEmpty(String mac) {
		if (StringUtils.isEmpty(mac)) {
			return true;
		}
		if (mac.equals(DEFAULT_MAC)) {
			return true;
		}
		return false;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAdSource() {
		return adSource;
	}

	public void setAdSource(String adSource) {
		this.adSource = adSource;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public Client clone() {
		Client client = null;
		try {
			client = (Client) super.clone();
		} catch (Exception e) {
			log.error("[action=clone_client]", e);
		}
		return client;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Client{" +
				"sn='" + sn + '\'' +
				", imei='" + imei + '\'' +
				", udid='" + udid + '\'' +
				", imsi='" + imsi + '\'' +
				", mac='" + mac + '\'' +
				", model='" + model + '\'' +
				", fw='" + fw + '\'' +
				", ua='" + ua + '\'' +
				", isp='" + isp + '\'' +
				", nt='" + nt + '\'' +
				", nw='" + nw + '\'' +
				", os='" + os + '\'' +
				", ver='" + ver + '\'' +
				", vc=" + vc +
				", ch='" + ch + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", lng='" + lng + '\'' +
				", lat='" + lat + '\'' +
				", pf='" + pf + '\'' +
				", ca='" + ca + '\'' +
				", ostype='" + ostype + '\'' +
				", ip='" + ip + '\'' +
				", adSource='" + adSource + '\'' +
				", pkg='" + pkg + '\'' +
				", area='" + area + '\'' +
				", time=" + time +
				", gaid='" + gaid + '\'' +
				", platform='" + platform + '\'' +
				", lang='" + lang + '\'' +
				", source='" + source + '\'' +
				'}';
	}
}
