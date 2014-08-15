package br.com.mkz.emailmarketing.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class DNSLookup {
	private static final String MX_ATTRIB = "MX";
	private static final String ADDR_ATTRIB = "A";
	private static String[] MX_ATTRIBS = { MX_ATTRIB };
	private static String[] ADDR_ATTRIBS = { ADDR_ATTRIB };

	private InitialDirContext idc;

	public DNSLookup() throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.dns.DnsContextFactory");
		idc = new InitialDirContext(env);
	}

	public List<String> getMXServers(String domain) throws NamingException {

		List<String> servers = new ArrayList<String>();
		Attributes attrs = idc.getAttributes(domain, MX_ATTRIBS);
		Attribute attr = attrs.get(MX_ATTRIB);

		if (attr != null) {
			for (int i = 0; i < attr.size(); i++) {
				String mxAttr = (String) attr.get(i);
				String[] parts = mxAttr.split(" ");

				// Split off the priority, and take the last field
				servers.add(parts[parts.length - 1]);
			}
		}

		return servers;
	}

	public List<String> getIPAddresses(String hostname) throws NamingException {

		List<String> ipAddresses = new ArrayList<String>();
		Attributes attrs = idc.getAttributes(hostname, ADDR_ATTRIBS);
		Attribute attr = attrs.get(ADDR_ATTRIB);

		if (attr != null) {
			for (int i = 0; i < attr.size(); i++) {
				ipAddresses.add((String) attr.get(i));
			}
		}

		return ipAddresses;
	}

	public String getRevName(String ipAddr) throws NamingException {

		String revName = null;
		String[] quads = ipAddr.split("\\.");

		// StringBuilder would be better, I know.
		ipAddr = "";

		for (int i = quads.length - 1; i >= 0; i--) {
			ipAddr += quads[i] + ".";
		}

		ipAddr += "in-addr.arpa.";
		Attributes attrs = idc.getAttributes(ipAddr, new String[] { "PTR" });
		Attribute attr = attrs.get("PTR");

		if (attr != null) {
			revName = (String) attr.get(0);
		}

		return revName;
	}

}
