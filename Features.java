/**
 * Class Features is the data type of data record 
 * <P>
 * Each variable in the class is an attribute of the data record. Each record has 42 attributes.  
 *
 * @author  Chandni Pakalapati
 *          Priyanka Samanta
 * @version 10-Dec-2015
 */

public class Features {

	double count,duration,wrong_fragment,urgent,
	hot,num_failed_logins,num_compromised,
	root_shell,su_attempted,num_root,
	num_file_creations,num_shells,
	num_access_files,num_outbound_cmds,
	srv_count,dst_host_count,
	dst_host_srv_count,land,logged_in,	
	is_host_login,is_guest_login,
	src_bytes,dst_bytes,
	serror_rate,srv_serror_rate,
	rerror_rate,srv_rerror_rate,
	same_srv_rate,diff_srv_rate,
	srv_diff_host_rate,dst_host_same_srv_rate,
	dst_host_diff_srv_rate,dst_host_same_src_port_rate,
	dst_host_srv_diff_host_rate,dst_host_serror_rate,
	dst_host_srv_serror_rate,dst_host_rerror_rate,
	dst_host_srv_rerror_rate,protocol_type,service,flag;
	String types_of_attacks;

	/**
	 * Construct a new Features object
	 */

	public Features(){
		this.duration=0.00;
		this.protocol_type=0.00;
		this.service=0.00;
		this.flag=0.00;
		this.src_bytes=0.00;
		this.dst_bytes=0.00;
		this.land=0.00;
		this.wrong_fragment=0.00;
		this.urgent=0.00;
		this.hot=0.00;
		this.num_failed_logins=0.00;
		this.logged_in=0.00;
		this.num_compromised=0.00;
		this.root_shell=0.00;
		this.su_attempted=0.00;
		this.num_root=0.00;
		this.num_file_creations=0.00;
		this.num_shells=0.00;
		this.num_access_files=0.00;
		this.num_outbound_cmds=0.00;
		this.is_host_login=0.00;
		this.is_guest_login=0.00;
		this.count=0.00;
		this.srv_count=0.00;
		this.serror_rate=0.00;
		this.srv_serror_rate=0.00;
		this.rerror_rate=0.00;
		this.srv_rerror_rate=0.00;
		this.same_srv_rate=0.00;
		this.diff_srv_rate=0.00;
		this.srv_diff_host_rate=0.00;
		this.dst_host_count=0.00;
		this.dst_host_srv_count=0.00;
		this.dst_host_same_srv_rate=0.00;
		this.dst_host_diff_srv_rate=0.00;
		this.dst_host_same_src_port_rate=0.00;
		this.dst_host_srv_diff_host_rate=0.00;
		this.dst_host_serror_rate=0.00;
		this.dst_host_srv_serror_rate=0.00;
		this.dst_host_rerror_rate=0.00;
		this.dst_host_srv_rerror_rate=0.00;
	}

	/**
	 * Construct a new Features object using parameterized constructor
	 */

	public Features (String [] features){
		this.duration=Double.parseDouble(features[0]);
		this.protocol_type=Double.parseDouble(features[1]);
		this.service=Double.parseDouble(features[2]);
		this.flag=Double.parseDouble(features[3]);
		this.src_bytes=Double.parseDouble(features[4]);
		this.dst_bytes=Double.parseDouble(features[5]);
		this.land=Double.parseDouble(features[6]);
		this.wrong_fragment=Double.parseDouble(features[7]);
		this.urgent=Double.parseDouble(features[8]);
		this.hot=Double.parseDouble(features[9]);
		this.num_failed_logins=Double.parseDouble(features[10]);
		this.logged_in=Double.parseDouble(features[11]);
		this.num_compromised=Double.parseDouble(features[12]);
		this.root_shell=Double.parseDouble(features[13]);
		this.su_attempted=Double.parseDouble(features[14]);
		this.num_root=Double.parseDouble(features[15]);
		this.num_file_creations=Double.parseDouble(features[16]);
		this.num_shells=Double.parseDouble(features[17]);
		this.num_access_files=Double.parseDouble(features[18]);
		this.num_outbound_cmds=Double.parseDouble(features[19]);
		this.is_host_login=Double.parseDouble(features[20]);
		this.is_guest_login=Double.parseDouble(features[21]);
		this.count=Double.parseDouble(features[22]);
		this.srv_count=Double.parseDouble(features[23]);
		this.serror_rate=Double.parseDouble(features[24]);
		this.srv_serror_rate=Double.parseDouble(features[25]);
		this.rerror_rate=Double.parseDouble(features[26]);
		this.srv_rerror_rate=Double.parseDouble(features[27]);
		this.same_srv_rate=Double.parseDouble(features[28]);
		this.diff_srv_rate=Double.parseDouble(features[29]);
		this.srv_diff_host_rate=Double.parseDouble(features[30]);
		this.dst_host_count=Double.parseDouble(features[31]);
		this.dst_host_srv_count=Double.parseDouble(features[32]);
		this.dst_host_same_srv_rate=Double.parseDouble(features[33]);
		this.dst_host_diff_srv_rate=Double.parseDouble(features[34]);
		this.dst_host_same_src_port_rate=Double.parseDouble(features[35]);
		this.dst_host_srv_diff_host_rate=Double.parseDouble(features[36]);
		this.dst_host_serror_rate=Double.parseDouble(features[37]);
		this.dst_host_srv_serror_rate=Double.parseDouble(features[38]);
		this.dst_host_rerror_rate=Double.parseDouble(features[39]);
		this.dst_host_srv_rerror_rate=Double.parseDouble(features[40]);
		this.types_of_attacks=features[41];

	}

	/**
	 * Construct a new Features object using parameterized constructor
	 */

	public Features(Features f)
	{
		this.duration=f.duration;
		this.protocol_type=f.protocol_type;
		this.service=f.service;
		this.flag=f.flag;
		this.src_bytes=f.src_bytes;
		this.dst_bytes=f.dst_bytes;
		this.land=f.land;
		this.wrong_fragment=f.wrong_fragment;
		this.urgent=f.urgent;
		this.hot=f.hot;
		this.num_failed_logins=f.num_failed_logins;
		this.logged_in=f.logged_in;
		this.num_compromised=f.num_compromised;
		this.root_shell=f.root_shell;
		this.su_attempted=f.su_attempted;
		this.num_root=f.num_root;
		this.num_file_creations=f.num_file_creations;
		this.num_shells=f.num_shells;
		this.num_access_files=f.num_access_files;
		this.num_outbound_cmds=f.num_outbound_cmds;
		this.is_host_login=f.is_host_login;
		this.is_guest_login=f.is_guest_login;
		this.count=f.count;
		this.srv_count=f.srv_count;
		this.serror_rate=f.serror_rate;
		this.srv_serror_rate=f.srv_serror_rate;
		this.rerror_rate=f.rerror_rate;
		this.srv_rerror_rate=f.srv_rerror_rate;
		this.same_srv_rate=f.same_srv_rate;
		this.diff_srv_rate=f.diff_srv_rate;
		this.srv_diff_host_rate=f.srv_diff_host_rate;
		this.dst_host_count=f.dst_host_count;
		this.dst_host_srv_count=f.dst_host_srv_count;
		this.dst_host_same_srv_rate=f.dst_host_same_srv_rate;
		this.dst_host_diff_srv_rate=f.dst_host_diff_srv_rate;
		this.dst_host_same_src_port_rate=f.dst_host_same_src_port_rate;
		this.dst_host_srv_diff_host_rate=f.dst_host_srv_diff_host_rate;
		this.dst_host_serror_rate=f.dst_host_serror_rate;
		this.dst_host_srv_serror_rate=f.dst_host_srv_serror_rate;
		this.dst_host_rerror_rate=f.dst_host_rerror_rate;
		this.dst_host_srv_rerror_rate=f.dst_host_srv_rerror_rate;
		this.types_of_attacks=f.types_of_attacks;
	}
	
	/**
	 * To retrieve the value of attribute duration
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDuration() {
		return duration;
	}
	
	/**
	 * To set the value of attribute duration
	 *
	 * @param  double duration value of the variable  
	 *
	 * @return void
	 */

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	/**
	 * To retrieve the value of attribute wrong_fragment
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getWrong_fragment() {
		return wrong_fragment;
	}

	/**
	 * To set the value of attribute wrong_fragment
	 *
	 * @param  double wrong_fragment value of the variable  
	 *
	 * @return void
	 */

	public void setWrong_fragment(double wrong_fragment) {
		this.wrong_fragment = wrong_fragment;
	}
	
	/**
	 * To retrieve the value of attribute wrong_fragment
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getUrgent() {
		return urgent;
	}
	
	/**
	 * To set the value of attribute urgent
	 *
	 * @param  double urgent value of the variable  
	 *
	 * @return void
	 */

	public void setUrgent(double urgent) {
		this.urgent = urgent;
	}

	/**
	 * To retrieve the value of attribute wrong_fragment
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getHot() {
		return hot;
	}
	
	/**
	 * To set the value of attribute hot
	 *
	 * @param  double hot value of the variable  
	 *
	 * @return void
	 */

	public void setHot(double hot) {
		this.hot = hot;
	}
	
	/**
	 * To retrieve the value of attribute num_failed_logins
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_failed_logins() {
		return num_failed_logins;
	}

	/**
	 * To set the value of attribute num_failed_logins
	 *
	 * @param  double num_failed_logins value of the variable  
	 *
	 * @return void
	 */

	public void setNum_failed_logins(double num_failed_logins) {
		this.num_failed_logins = num_failed_logins;
	}

	/**
	 * To retrieve the value of attribute num_compromised
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_compromised() {
		return num_compromised;
	}
	
	/**
	 * To set the value of attribute num_compromised
	 *
	 * @param  double num_compromised value of the variable  
	 *
	 * @return void
	 */

	public void setNum_compromised(double num_compromised) {
		this.num_compromised = num_compromised;
	}

	/**
	 * To retrieve the value of attribute root_shell
	 *
	 * @param  void  
	 *
	 * @return  double
	 */
	
	public double getRoot_shell() {
		return root_shell;
	}
	
	/**
	 * To set the value of attribute root_shell
	 *
	 * @param  double root_shell value of the variable  
	 *
	 * @return void
	 */

	public void setRoot_shell(double root_shell) {
		this.root_shell = root_shell;
	}

	/**
	 * To retrieve the value of attribute su_attempted
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSu_attempted() {
		return su_attempted;
	}
	
	/**
	 * To set the value of attribute su_attempted
	 *
	 * @param  double su_attempted value of the variable  
	 *
	 * @return void
	 */

	public void setSu_attempted(double su_attempted) {
		this.su_attempted = su_attempted;
	}

	/**
	 * To retrieve the value of attribute num_root
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_root() {
		return num_root;
	}
	
	/**
	 * To set the value of attribute num_root
	 *
	 * @param  double num_root value of the variable  
	 *
	 * @return void
	 */

	public void setNum_root(double num_root) {
		this.num_root = num_root;
	}
	
	/**
	 * To retrieve the value of attribute num_file_creations
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_file_creations() {
		return num_file_creations;
	}
	
	/**
	 * To set the value of attribute num_file_creations
	 *
	 * @param  double num_file_creations value of the variable  
	 *
	 * @return void
	 */

	public void setNum_file_creations(double num_file_creations) {
		this.num_file_creations = num_file_creations;
	}

	/**
	 * To retrieve the value of attribute num_shells
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_shells() {
		return num_shells;
	}
	
	/**
	 * To set the value of attribute num_shells
	 *
	 * @param  double num_shells value of the variable  
	 *
	 * @return void
	 */

	public void setNum_shells(double num_shells) {
		this.num_shells = num_shells;
	}

	/**
	 * To retrieve the value of attribute num_access_files
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_access_files() {
		return num_access_files;
	}
	
	/**
	 * To set the value of attribute num_access_files
	 *
	 * @param  double num_access_files value of the variable  
	 *
	 * @return void
	 */

	public void setNum_access_files(double num_access_files) {
		this.num_access_files = num_access_files;
	}

	/**
	 * To retrieve the value of attribute num_outbound_cmds
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getNum_outbound_cmds() {
		return num_outbound_cmds;
	}
	
	/**
	 * To set the value of attribute num_outbound_cmds
	 *
	 * @param  double num_outbound_cmds value of the variable  
	 *
	 * @return void
	 */

	public void setNum_outbound_cmds(double num_outbound_cmds) {
		this.num_outbound_cmds = num_outbound_cmds;
	}

	/**
	 * To retrieve the value of attribute count
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getCount() {
		return count;
	}

	/**
	 * To set the value of attribute count
	 *
	 * @param  double count value of the variable  
	 *
	 * @return void
	 */
	
	public void setCount(double count) {
		this.count = count;
	}

	/**
	 * To retrieve the value of attribute srv_count
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSrv_count() {
		return srv_count;
	}

	/**
	 * To set the value of attribute srv_count
	 *
	 * @param  double srv_count value of the variable  
	 *
	 * @return void
	 */

	public void setSrv_count(double srv_count) {
		this.srv_count = srv_count;
	}

	/**
	 * To retrieve the value of attribute dst_host_count
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_count() {
		return dst_host_count;
	}

	/**
	 * To set the value of attribute dst_host_count
	 *
	 * @param  double dst_host_count value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_count(double dst_host_count) {
		this.dst_host_count = dst_host_count;
	}

	/**
	 * To retrieve the value of attribute dst_host_srv_count
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_srv_count() {
		return dst_host_srv_count;
	}

	/**
	 * To set the value of attribute dst_host_srv_count
	 *
	 * @param  double dst_host_srv_count value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_srv_count(double dst_host_srv_count) {
		this.dst_host_srv_count = dst_host_srv_count;
	}

	/**
	 * To retrieve the value of attribute land
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getLand() {
		return land;
	}

	/**
	 * To set the value of attribute land
	 *
	 * @param  double land value of the variable  
	 *
	 * @return void
	 */

	public void setLand(double land) {
		this.land = land;
	}

	/**
	 * To retrieve the value of attribute logged_in
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getLogged_in() {
		return logged_in;
	}

	/**
	 * To set the value of attribute logged_in
	 *
	 * @param  double logged_in value of the variable  
	 *
	 * @return void
	 */

	public void setLogged_in(double logged_in) {
		this.logged_in = logged_in;
	}

	/**
	 * To retrieve the value of attribute is_host_login
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getIs_host_login() {
		return is_host_login;
	}

	/**
	 * To set the value of attribute is_host_login
	 *
	 * @param  double is_host_login value of the variable  
	 *
	 * @return void
	 */

	public void setIs_host_login(double is_host_login) {
		this.is_host_login = is_host_login;
	}

	/**
	 * To retrieve the value of attribute is_guest_login
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getIs_guest_login() {
		return is_guest_login;
	}

	/**
	 * To set the value of attribute is_guest_login
	 *
	 * @param  double is_guest_login value of the variable  
	 *
	 * @return void
	 */

	public void setIs_guest_login(double is_guest_login) {
		this.is_guest_login = is_guest_login;
	}

	/**
	 * To retrieve the value of attribute src_bytes
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSrc_bytes() {
		return src_bytes;
	}

	/**
	 * To set the value of attribute src_bytes
	 *
	 * @param  double src_bytes value of the variable  
	 *
	 * @return void
	 */

	public void setSrc_bytes(double src_bytes) {
		this.src_bytes = src_bytes;
	}

	/**
	 * To retrieve the value of attribute dst_bytes
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_bytes() {
		return dst_bytes;
	}

	/**
	 * To set the value of attribute dst_bytes
	 *
	 * @param  double dst_bytes value of the variable  
	 *
	 * @return void
	 */

	public void setDst_bytes(double dst_bytes) {
		this.dst_bytes = dst_bytes;
	}

	/**
	 * To retrieve the value of attribute serror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSerror_rate() {
		return serror_rate;
	}

	/**
	 * To set the value of attribute serror_rate
	 *
	 * @param  double serror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setSerror_rate(double serror_rate) {
		this.serror_rate = serror_rate;
	}

	/**
	 * To retrieve the value of attribute srv_serror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSrv_serror_rate() {
		return srv_serror_rate;
	}

	/**
	 * To set the value of attribute srv_serror_rate
	 *
	 * @param  double srv_serror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setSrv_serror_rate(double srv_serror_rate) {
		this.srv_serror_rate = srv_serror_rate;
	}

	/**
	 * To retrieve the value of attribute rerror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getRerror_rate() {
		return rerror_rate;
	}

	/**
	 * To set the value of attribute rerror_rate
	 *
	 * @param  double rerror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setRerror_rate(double rerror_rate) {
		this.rerror_rate = rerror_rate;
	}

	/**
	 * To retrieve the value of attribute srv_rerror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSrv_rerror_rate() {
		return srv_rerror_rate;
	}

	/**
	 * To set the value of attribute srv_rerror_rate
	 *
	 * @param  double srv_rerror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setSrv_rerror_rate(double srv_rerror_rate) {
		this.srv_rerror_rate = srv_rerror_rate;
	}

	/**
	 * To retrieve the value of attribute same_srv_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSame_srv_rate() {
		return same_srv_rate;
	}

	/**
	 * To set the value of attribute same_srv_rate
	 *
	 * @param  double same_srv_rate value of the variable  
	 *
	 * @return void
	 */

	public void setSame_srv_rate(double same_srv_rate) {
		this.same_srv_rate = same_srv_rate;
	}

	/**
	 * To retrieve the value of attribute diff_srv_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDiff_srv_rate() {
		return diff_srv_rate;
	}

	/**
	 * To set the value of attribute diff_srv_rate
	 *
	 * @param  double diff_srv_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDiff_srv_rate(double diff_srv_rate) {
		this.diff_srv_rate = diff_srv_rate;
	}

	/**
	 * To retrieve the value of attribute srv_diff_host_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getSrv_diff_host_rate() {
		return srv_diff_host_rate;
	}

	/**
	 * To set the value of attribute srv_diff_host_rate
	 *
	 * @param  double srv_diff_host_rate value of the variable  
	 *
	 * @return void
	 */

	public void setSrv_diff_host_rate(double srv_diff_host_rate) {
		this.srv_diff_host_rate = srv_diff_host_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_same_srv_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_same_srv_rate() {
		return dst_host_same_srv_rate;
	}

	/**
	 * To set the value of attribute dst_host_same_srv_rate
	 *
	 * @param  double dst_host_same_srv_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_same_srv_rate(double dst_host_same_srv_rate) {
		this.dst_host_same_srv_rate = dst_host_same_srv_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_diff_srv_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_diff_srv_rate() {
		return dst_host_diff_srv_rate;
	}

	/**
	 * To set the value of attribute dst_host_diff_srv_rate
	 *
	 * @param  double dst_host_diff_srv_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_diff_srv_rate(double dst_host_diff_srv_rate) {
		this.dst_host_diff_srv_rate = dst_host_diff_srv_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_same_src_port_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_same_src_port_rate() {
		return dst_host_same_src_port_rate;
	}

	/**
	 * To set the value of attribute dst_host_same_src_port_rate
	 *
	 * @param  double dst_host_same_src_port_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_same_src_port_rate(double dst_host_same_src_port_rate) {
		this.dst_host_same_src_port_rate = dst_host_same_src_port_rate;
	}

	/**
	 * To retrieve the value of attribute  dst_host_srv_diff_host_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_srv_diff_host_rate() {
		return dst_host_srv_diff_host_rate;
	}

	/**
	 * To set the value of attribute dst_host_srv_diff_host_rate
	 *
	 * @param  double dst_host_srv_diff_host_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_srv_diff_host_rate(double dst_host_srv_diff_host_rate) {
		this.dst_host_srv_diff_host_rate = dst_host_srv_diff_host_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_serror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_serror_rate() {
		return dst_host_serror_rate;
	}

	/**
	 * To set the value of attribute dst_host_serror_rate
	 *
	 * @param  double dst_host_serror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_serror_rate(double dst_host_serror_rate) {
		this.dst_host_serror_rate = dst_host_serror_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_srv_serror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_srv_serror_rate() {
		return dst_host_srv_serror_rate;
	}

	/**
	 * To set the value of attribute dst_host_srv_serror_rate)
	 *
	 * @param  double dst_host_srv_serror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_srv_serror_rate(double dst_host_srv_serror_rate) {
		this.dst_host_srv_serror_rate = dst_host_srv_serror_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_rerror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_rerror_rate() {
		return dst_host_rerror_rate;
	}

	/**
	 * To set the value of attribute dst_host_rerror_rate
	 *
	 * @param  double dst_host_rerror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_rerror_rate(double dst_host_rerror_rate) {
		this.dst_host_rerror_rate = dst_host_rerror_rate;
	}

	/**
	 * To retrieve the value of attribute dst_host_srv_rerror_rate
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getDst_host_srv_rerror_rate() {
		return dst_host_srv_rerror_rate;
	}

	/**
	 * To set the value of attribute dst_host_srv_rerror_rate
	 *
	 * @param  double dst_host_srv_rerror_rate value of the variable  
	 *
	 * @return void
	 */

	public void setDst_host_srv_rerror_rate(double dst_host_srv_rerror_rate) {
		this.dst_host_srv_rerror_rate = dst_host_srv_rerror_rate;
	}

	/**
	 * To retrieve the value of attribute protocol_type
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getProtocol_type() {
		return protocol_type;
	}

	/**
	 * To set the value of attribute protocol_type
	 *
	 * @param  double protocol_type value of the variable  
	 *
	 * @return void
	 */

	public void setProtocol_type(double protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * To retrieve the value of attribute service
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getService() {
		return service;
	}

	/**
	 * To set the value of attribute service
	 *
	 * @param  double service value of the variable  
	 *
	 * @return void
	 */

	public void setService(double service) {
		this.service = service;
	}

	/**
	 * To retrieve the value of attribute flag
	 *
	 * @param  void  
	 *
	 * @return  double
	 */

	public double getFlag() {
		return flag;
	}

	/**
	 * To set the value of attribute flag
	 *
	 * @param  double flag value of the variable  
	 *
	 * @return void
	 */

	public void setFlag(double flag) {
		this.flag = flag;
	}

	/**
	 * To retrieve the value of attribute types_of_attacks
	 *
	 * @param  void  
	 *
	 * @return  String
	 */

	public String getTypes_of_attacks() {
		return types_of_attacks;
	}

	/**
	 * To set the value of attribute types_of_attacks
	 *
	 * @param  String types_of_attacks value of the variable  
	 *
	 * @return void
	 */

	public void setTypes_of_attacks(String types_of_attacks) {
		this.types_of_attacks = types_of_attacks;
	}

	/**
	 * To print the object 
	 *
	 * @param  void  
	 *
	 * @return void 
	 */

	public void print(){
		System.out.println(duration + " " +
				protocol_type + " " +
				service + " " +
				flag + " " +
				src_bytes + " " +
				dst_bytes + " " +
				land + " " +
				wrong_fragment + " " +
				urgent + " " +
				hot + " " +
				num_failed_logins + " " +
				logged_in + " " +
				num_compromised + " " +
				root_shell + " " +
				su_attempted + " " +
				num_root + " " +
				num_file_creations + " " +
				num_shells + " " +
				num_access_files + " " +
				num_outbound_cmds + " " +
				is_host_login + " " +
				is_guest_login + " " +
				count + " " +
				srv_count + " " +
				serror_rate + " " +
				srv_serror_rate + " " +
				rerror_rate + " " +
				srv_rerror_rate + " " +
				same_srv_rate + " " +
				diff_srv_rate + " " +
				srv_diff_host_rate + " " +
				dst_host_count + " " +
				dst_host_srv_count + " " +
				dst_host_same_srv_rate + " " +
				dst_host_diff_srv_rate + " " +
				dst_host_same_src_port_rate + " " +
				dst_host_srv_diff_host_rate + " " +
				dst_host_serror_rate + " " +
				dst_host_srv_serror_rate + " " +
				dst_host_rerror_rate + " " +
				dst_host_srv_rerror_rate + " " +
				types_of_attacks);
	}
}
