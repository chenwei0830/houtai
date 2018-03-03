package com.thinkgem.jeesite.common.supcan.freeform;

import com.thinkgem.jeesite.common.supcan.common.Common;
import com.thinkgem.jeesite.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author LM
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
