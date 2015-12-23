import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import edu.rit.util.Random;
import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

/**
 * Class kMeansClusteringSmp is a multicore parallel program that forms  
 * clusters of different network attack types
 * <P>
 * Usage: java pj2 threads=<K> schedule=guided kMeansClusteringSmp <DataSet> <seed>
 * DataSet = path of the dataset file
 * seed = Random number seed
 *
 * @author  Chandni Pakalapati
	    Priyanka Samanta
 * @version 10-Dec-2015
 */

public class kMeansClusteringSmp extends Task {

	//array list to store all the data points
	static ArrayList<Features> globaldataPoints = new ArrayList<Features>();
	static int cluster_labels[][] = new int[5][5];
	static int seed;
	static int k = 4;

	//Global Reduction Variable
	SSEVbl ssevbl;

	//main program
	public void main(String args[]) throws Exception{

		// Validate command line arguments.
		if (args.length !=2) usage(); 
		String fileName = args[0];
		seed = Integer.parseInt(args[1]);

		//reading data points from file and store them in an arraylist
		BufferedReader in;

		try {
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while((line=in.readLine())!=null){
				String[] features = line.split(",");
				Features features_norm = new Features(features); 
				globaldataPoints.add(features_norm);
			}
			ssevbl= new SSEVbl();
			
			//Parallelsing the number of iterations
			parallelFor(0, 99).schedule(guided).exec(new Loop(){
				SSEVbl thrssevbl;
				ArrayList<Features> dataPoints;
				Random prng;
			      //  long start;
				//long end;
						
				//initialsing the thread lcoal variables
				public void start(){
					thrssevbl = threadLocal(ssevbl);
					dataPoints = globaldataPoints;
				//	start = 0;
				//	end = 0;
					prng = new Random(seed + rank());
				}
					
				//run method to find the cluster for each iteration
				public void run(int iter){
					//store the initial centroids
					ArrayList<Features> centroid_list=new ArrayList<Features>();
					//generate initial centroids randomly
					for (int i=0;i<k;i++){
						int random_index=prng.nextInt(dataPoints.size());
						centroid_list.add(new Features(dataPoints.get(random_index)));
					}
					//Hashmap to store the clusters
					HashMap<Integer,ArrayList<Integer>> pointClusterMap;
					
				//	start = System.currentTimeMillis();
					//run the loop till it converges
					while(true){
						pointClusterMap =new HashMap<Integer,ArrayList<Integer>>(); 
						
						//calculate distance of every point with every centroid
						for (int j=0;j<dataPoints.size();j++){
							double temp_distance=Double.MAX_VALUE;
							int cluster_id=-1;
							//finding the min distance of a point with all centroids
							for(int i=0;i<k;i++){
								//finding the min distance of a point with all centroids
								double distance=getDistance
										(centroid_list.get(i),dataPoints.get(j));
								if(distance<temp_distance){
									temp_distance=distance;
									cluster_id=i;
								}
							}
							//assign point to the centroid
							if (!pointClusterMap.containsKey(cluster_id)){
								ArrayList<Integer> clusterTemp = new ArrayList<Integer>();
								clusterTemp.add(j);
								pointClusterMap.put(cluster_id, clusterTemp);
							}
							else{
								pointClusterMap.get(cluster_id).add(j);
							}
						}

						//arraylist to store new centroids
						ArrayList<Features> new_centroidList = new ArrayList<Features>();

						//calculate new centroids
						for (int i=0;i<k;i++){
							Features new_centroid= getNewCentroid(pointClusterMap.get(i),dataPoints);
							if (new_centroid==null){
								new_centroidList.add(centroid_list.get(i));
							}
							else{
								new_centroidList.add(new_centroid);
							}
						}

						//distance between old and new Centroid
						double centroid_distancewithEachOther=0.0;
						for (int i=0;i<new_centroidList.size();i++){
							centroid_distancewithEachOther+=getDistance
									(new_centroidList.get(i), centroid_list.get(i));
						}
				
						//converging condition to exit from infinite loop
						if(centroid_distancewithEachOther==0){
							break;
						}
						else{
							//replace centroid list with new centroid
							for (int i=0;i< centroid_list.size();i++){
								centroid_list.remove(i);
								centroid_list.add(i, new_centroidList.get(i));
							}
						}
					}
		//			end = System.currentTimeMillis();
		//			long diff = end-start;
//					System.out.println("while time " + rank() + " iteration " + iter + " " + diff);

					//Calculate Sum of Squared Errors(SSE)
					double distance = 0.0;
					//summation of distances of points in a cluster with their centroids
					for(int i=0;i<centroid_list.size();i++){
						if(pointClusterMap.get(i)==null){
							continue;
						}
						else{
							for(int j=0;j<pointClusterMap.get(i).size();j++){
								distance += Math.pow(getDistance(centroid_list.get(i),dataPoints.get(pointClusterMap.get(i).get(j))), 2);
							}
						}
					}
					thrssevbl.minSSE(distance,pointClusterMap);
				}

			});
			//to label the final cluster
			findAttackMappings(ssevbl.finalClusterMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To get the new centroids
	 * by finding the average of the data points present in the cluster
	 *
	 * @param  mapValue  ArrayList<Integer> cluster containing the data points 
	 *
	 * @return  newCentroid Features
	 */
	
	public static Features getNewCentroid(ArrayList<Integer> mapValue,ArrayList<Features> dataPoints){
		Features newCentroid = new Features();
		if(mapValue==null){
			return null;
		}
		else{
			for(int i=0;i<mapValue.size();i++){
				newCentroid.setDuration(newCentroid.getDuration()+
						dataPoints.get(mapValue.get(i)).getDuration());
				newCentroid.setProtocol_type(newCentroid.getProtocol_type()+ 
						dataPoints.get(mapValue.get(i)).getProtocol_type());
				newCentroid.setService(newCentroid.getService()+ 
						dataPoints.get(mapValue.get(i)).getService());
				newCentroid.setFlag(newCentroid.getFlag()+ 
						dataPoints.get(mapValue.get(i)).getFlag());
				newCentroid.setSrc_bytes(newCentroid.getSrc_bytes()+ 
						dataPoints.get(mapValue.get(i)).getSrc_bytes());
				newCentroid.setDst_bytes(newCentroid.getDst_bytes()+ 
						dataPoints.get(mapValue.get(i)).getDst_bytes());
				newCentroid.setLand(newCentroid.getLand()+ 
						dataPoints.get(mapValue.get(i)).getLand());
				newCentroid.setWrong_fragment(newCentroid.getWrong_fragment()+
						dataPoints.get(mapValue.get(i)).getWrong_fragment());
				newCentroid.setUrgent(newCentroid.getUrgent()+ 
						dataPoints.get(mapValue.get(i)).getUrgent());
				newCentroid.setHot(newCentroid.getHot()+ 
						dataPoints.get(mapValue.get(i)).getHot());
				newCentroid.setNum_failed_logins(newCentroid.getNum_failed_logins()+
						dataPoints.get(mapValue.get(i)).getNum_failed_logins());
				newCentroid.setLogged_in(newCentroid.getLogged_in()+
						dataPoints.get(mapValue.get(i)).getLogged_in());
				newCentroid.setNum_compromised(newCentroid.getNum_compromised()+ 
						dataPoints.get(mapValue.get(i)).getNum_compromised());
				newCentroid.setRoot_shell(newCentroid.getRoot_shell()+ 
						dataPoints.get(mapValue.get(i)).getRoot_shell());
				newCentroid.setSu_attempted(newCentroid.getSu_attempted()+ 
						dataPoints.get(mapValue.get(i)).getSu_attempted());
				newCentroid.setNum_root(newCentroid.getNum_root()+ 
						dataPoints.get(mapValue.get(i)).getNum_root());
				newCentroid.setNum_file_creations(newCentroid.getNum_file_creations()+ 
						dataPoints.get(mapValue.get(i)).getNum_file_creations());
				newCentroid.setNum_shells(newCentroid.getNum_shells()+
						dataPoints.get(mapValue.get(i)).getNum_shells());
				newCentroid.setNum_access_files(newCentroid.getNum_access_files()+ 
						dataPoints.get(mapValue.get(i)).getNum_access_files());
				newCentroid.setNum_outbound_cmds(newCentroid.getNum_outbound_cmds()+
						dataPoints.get(mapValue.get(i)).getNum_outbound_cmds());
				newCentroid.setIs_host_login(newCentroid.getIs_host_login()+ 
						dataPoints.get(mapValue.get(i)).getIs_host_login());
				newCentroid.setIs_guest_login(newCentroid.getIs_guest_login()+
						dataPoints.get(mapValue.get(i)).getIs_guest_login());
				newCentroid.setCount(newCentroid.getCount()+
						dataPoints.get(mapValue.get(i)).getCount());
				newCentroid.setSrv_count(newCentroid.getSrv_count()+ 
						dataPoints.get(mapValue.get(i)).getSrv_count());
				newCentroid.setSerror_rate(newCentroid.getSerror_rate()+
						dataPoints.get(mapValue.get(i)).getSerror_rate());
				newCentroid.setSrv_serror_rate(newCentroid.getSrv_serror_rate()+
						dataPoints.get(mapValue.get(i)).getSrv_serror_rate());
				newCentroid.setRerror_rate(newCentroid.getRerror_rate()+
						dataPoints.get(mapValue.get(i)).getRerror_rate());
				newCentroid.setSrv_rerror_rate(newCentroid.getSrv_rerror_rate()+
						dataPoints.get(mapValue.get(i)).getSrv_rerror_rate());
				newCentroid.setSame_srv_rate(newCentroid.getSame_srv_rate()+ 
						dataPoints.get(mapValue.get(i)).getSame_srv_rate());
				newCentroid.setDiff_srv_rate(newCentroid.getDiff_srv_rate()+ 
						dataPoints.get(mapValue.get(i)).getDiff_srv_rate());
				newCentroid.setSrv_diff_host_rate(newCentroid.getSrv_diff_host_rate()+ 
						dataPoints.get(mapValue.get(i)).getSrv_diff_host_rate());
				newCentroid.setDst_host_count(newCentroid.getDst_host_count()+ 
						dataPoints.get(mapValue.get(i)).getDst_host_count());
				newCentroid.setDst_host_srv_count(newCentroid.getDst_host_srv_count()+ 
						dataPoints.get(mapValue.get(i)).getDst_host_srv_count());
				newCentroid.setDst_host_same_srv_rate(newCentroid.getDst_host_same_srv_rate()+
						dataPoints.get(mapValue.get(i)).getDst_host_same_srv_rate());
				newCentroid.setDst_host_diff_srv_rate(newCentroid.getDst_host_diff_srv_rate()+ 
						dataPoints.get(mapValue.get(i)).getDst_host_diff_srv_rate());
				newCentroid.setDst_host_same_src_port_rate(newCentroid.getDst_host_same_src_port_rate()+ 
						dataPoints.get(mapValue.get(i)).getDst_host_same_src_port_rate());
				newCentroid.setDst_host_srv_diff_host_rate(newCentroid.getDst_host_srv_diff_host_rate()+
						dataPoints.get(mapValue.get(i)).getDst_host_srv_diff_host_rate());
				newCentroid.setDst_host_serror_rate(newCentroid.getDst_host_serror_rate()+
						dataPoints.get(mapValue.get(i)).getDst_host_serror_rate());
				newCentroid.setDst_host_srv_serror_rate(newCentroid.getDst_host_srv_serror_rate()+ 
						dataPoints.get(mapValue.get(i)).getDst_host_srv_serror_rate());
				newCentroid.setDst_host_rerror_rate(newCentroid.getDst_host_rerror_rate()+
						dataPoints.get(mapValue.get(i)).getDst_host_rerror_rate());
				newCentroid.setDst_host_srv_rerror_rate(newCentroid.getDst_host_srv_rerror_rate()+ 
						dataPoints.get(mapValue.get(i)).getDst_host_srv_rerror_rate());
			}
			//finding the average
			newCentroid.setDuration(newCentroid.getDuration()/mapValue.size());
			newCentroid.setProtocol_type(newCentroid.getProtocol_type()/mapValue.size());
			newCentroid.setService(newCentroid.getService()/mapValue.size());
			newCentroid.setFlag(newCentroid.getFlag()/mapValue.size());
			newCentroid.setSrc_bytes(newCentroid.getSrc_bytes()/mapValue.size());
			newCentroid.setDst_bytes(newCentroid.getDst_bytes()/mapValue.size());
			newCentroid.setLand(newCentroid.getLand()/mapValue.size());
			newCentroid.setWrong_fragment(newCentroid.getWrong_fragment()/mapValue.size());
			newCentroid.setUrgent(newCentroid.getUrgent()/mapValue.size());
			newCentroid.setHot(newCentroid.getHot()/mapValue.size());
			newCentroid.setNum_failed_logins(newCentroid.getNum_failed_logins()/mapValue.size());
			newCentroid.setLogged_in(newCentroid.getLogged_in()/mapValue.size());
			newCentroid.setNum_compromised(newCentroid.getNum_compromised()/mapValue.size());
			newCentroid.setRoot_shell(newCentroid.getRoot_shell()/mapValue.size());
			newCentroid.setSu_attempted(newCentroid.getSu_attempted()/mapValue.size());
			newCentroid.setNum_root(newCentroid.getNum_root()/mapValue.size());
			newCentroid.setNum_file_creations(newCentroid.getNum_file_creations()/mapValue.size());
			newCentroid.setNum_shells(newCentroid.getNum_shells()/mapValue.size());
			newCentroid.setNum_access_files(newCentroid.getNum_access_files()/mapValue.size());
			newCentroid.setNum_outbound_cmds(newCentroid.getNum_outbound_cmds()/mapValue.size());
			newCentroid.setIs_host_login(newCentroid.getIs_host_login()/mapValue.size());
			newCentroid.setIs_guest_login(newCentroid.getIs_guest_login()/mapValue.size());
			newCentroid.setCount(newCentroid.getCount()/mapValue.size());
			newCentroid.setSrv_count(newCentroid.getSrv_count()/mapValue.size());
			newCentroid.setSerror_rate(newCentroid.getSerror_rate()/mapValue.size());
			newCentroid.setSrv_serror_rate(newCentroid.getSrv_serror_rate()/mapValue.size());
			newCentroid.setRerror_rate(newCentroid.getRerror_rate()/mapValue.size());
			newCentroid.setSrv_rerror_rate(newCentroid.getSrv_rerror_rate()/mapValue.size());
			newCentroid.setSame_srv_rate(newCentroid.getSame_srv_rate()/mapValue.size());
			newCentroid.setDiff_srv_rate(newCentroid.getDiff_srv_rate()/mapValue.size());
			newCentroid.setSrv_diff_host_rate(newCentroid.getSrv_diff_host_rate()/mapValue.size());
			newCentroid.setDst_host_count(newCentroid.getDst_host_count()/mapValue.size());
			newCentroid.setDst_host_srv_count(newCentroid.getDst_host_srv_count()/mapValue.size());
			newCentroid.setDst_host_same_srv_rate(newCentroid.getDst_host_same_srv_rate()/mapValue.size());
			newCentroid.setDst_host_diff_srv_rate(newCentroid.getDst_host_diff_srv_rate()/mapValue.size());
			newCentroid.setDst_host_same_src_port_rate(newCentroid.getDst_host_same_src_port_rate()/mapValue.size());
			newCentroid.setDst_host_srv_diff_host_rate(newCentroid.getDst_host_srv_diff_host_rate()/mapValue.size());
			newCentroid.setDst_host_serror_rate(newCentroid.getDst_host_serror_rate()/mapValue.size());
			newCentroid.setDst_host_srv_serror_rate(newCentroid.getDst_host_srv_serror_rate()/mapValue.size());
			newCentroid.setDst_host_rerror_rate(newCentroid.getDst_host_rerror_rate()/mapValue.size());
			newCentroid.setDst_host_srv_rerror_rate(newCentroid.getDst_host_srv_rerror_rate()/mapValue.size());

			return newCentroid;
		}
	}

	/**
	 * To find distance between two points of the type Features
	 *	 
	 * @param  iCentroid, jPoint Features data points 
	 *
	 * @return  distance double
	 */
	public static double getDistance
	(Features iCentroid, Features jPoint){
		double distance=0.0;
		distance=Math.sqrt(Math.pow(iCentroid.getDuration() - jPoint.getDuration(), 2)+
				Math.pow(iCentroid.getProtocol_type() - jPoint.getProtocol_type(), 2)+
				Math.pow(iCentroid.getService() - jPoint.getService(), 2)+
				Math.pow(iCentroid.getFlag() - jPoint.getFlag(), 2)+
				Math.pow(iCentroid.getSrc_bytes() - jPoint.getSrc_bytes(), 2)+
				Math.pow(iCentroid.getDst_bytes() - jPoint.getDst_bytes(), 2)+
				Math.pow(iCentroid.getLand() - jPoint.getLand(), 2)+
				Math.pow(iCentroid.getWrong_fragment() - jPoint.getWrong_fragment(), 2)+
				Math.pow(iCentroid.getUrgent() - jPoint.getUrgent(), 2)+
				Math.pow(iCentroid.getHot() - jPoint.getHot(), 2)+
				Math.pow(iCentroid.getNum_failed_logins() - jPoint.getNum_failed_logins(), 2)+
				Math.pow(iCentroid.getLogged_in() - jPoint.getLogged_in(), 2)+
				Math.pow(iCentroid.getNum_compromised() - jPoint.getNum_compromised(), 2)+
				Math.pow(iCentroid.getRoot_shell() - jPoint.getRoot_shell(), 2)+
				Math.pow(iCentroid.getSu_attempted() - jPoint.getSu_attempted(), 2)+
				Math.pow(iCentroid.getNum_root() - jPoint.getNum_root(), 2)+
				Math.pow(iCentroid.getNum_file_creations() - jPoint.getNum_file_creations(), 2)+
				Math.pow(iCentroid.getNum_shells() - jPoint.getNum_shells(), 2)+
				Math.pow(iCentroid.getNum_access_files() - jPoint.getNum_access_files(), 2)+
				Math.pow(iCentroid.getNum_outbound_cmds() - jPoint.getNum_outbound_cmds(), 2)+
				Math.pow(iCentroid.getIs_host_login() - jPoint.getIs_host_login(), 2)+
				Math.pow(iCentroid.getIs_guest_login() - jPoint.getIs_guest_login(), 2)+
				Math.pow(iCentroid.getCount() - jPoint.getCount(), 2)+
				Math.pow(iCentroid.getSrv_count() - jPoint.getSrv_count(), 2)+
				Math.pow(iCentroid.getSerror_rate() - jPoint.getSerror_rate(), 2)+
				Math.pow(iCentroid.getSrv_serror_rate() - jPoint.getSrv_serror_rate(), 2)+
				Math.pow(iCentroid.getRerror_rate() - jPoint.getRerror_rate(), 2)+
				Math.pow(iCentroid.getSrv_rerror_rate() - jPoint.getSrv_rerror_rate(), 2)+
				Math.pow(iCentroid.getSame_srv_rate() - jPoint.getSame_srv_rate(), 2)+
				Math.pow(iCentroid.getDiff_srv_rate() - jPoint.getDiff_srv_rate(), 2)+
				Math.pow(iCentroid.getSrv_diff_host_rate() - jPoint.getSrv_diff_host_rate(), 2)+
				Math.pow(iCentroid.getDst_host_count() - jPoint.getDst_host_count(), 2)+
				Math.pow(iCentroid.getDst_host_srv_count() - jPoint.getDst_host_srv_count(), 2)+
				Math.pow(iCentroid.getDst_host_same_srv_rate() - jPoint.getDst_host_same_srv_rate(), 2)+
				Math.pow(iCentroid.getDst_host_diff_srv_rate() - jPoint.getDst_host_diff_srv_rate(), 2)+
				Math.pow(iCentroid.getDst_host_same_src_port_rate() - jPoint.getDst_host_same_src_port_rate(), 2)+
				Math.pow(iCentroid.getDst_host_srv_diff_host_rate() - jPoint.getDst_host_srv_diff_host_rate(), 2)+
				Math.pow(iCentroid.getDst_host_serror_rate() - jPoint.getDst_host_serror_rate(), 2)+
				Math.pow(iCentroid.getDst_host_srv_serror_rate() - jPoint.getDst_host_srv_serror_rate(), 2)+
				Math.pow(iCentroid.getDst_host_rerror_rate() - jPoint.getDst_host_rerror_rate(), 2)+
				Math.pow(iCentroid.getDst_host_srv_rerror_rate() - jPoint.getDst_host_srv_rerror_rate(), 2));

		return distance;
	}

		
	/**
	 * To label the clusters
	 *
	 * @param  finalClusters HashMap<Integer,ArrayList<Integer>> the final clusters stored in a map 
	 *
	 * @return  void
	 */

	public static void findAttackMappings
	(HashMap<Integer,ArrayList<Integer>> finalClusters){

		//to count the occurrence of each attack types in all clusters
		for (int i=0;i<finalClusters.size();i++){

			//declaring map for every cluster
			HashMap<String,Integer> attackMap = new HashMap<String,Integer>();
			attackMap.put("Normal", 0);
			attackMap.put("DOS", 0);
			attackMap.put("Probe", 0);
			attackMap.put("R2L", 0);
			attackMap.put("U2R", 0);

			//to count the occurence of each attack type in a cluster
			for(int j=0;j<finalClusters.get(i).size();j++){
				String attackType=globaldataPoints.
						get(finalClusters.get(i).get(j)).getTypes_of_attacks();
				//normal
				if(attackType.equals("normal.")){
					attackMap.put("Normal", (attackMap.get("Normal"))+1);
				}

				//DOS
				else if((attackType.equals("back."))||(attackType.equals("land."))||
						(attackType.equals("neptune."))||(attackType.equals("pod."))||
						(attackType.equals("smurf."))||(attackType.equals("teardrop."))){
					attackMap.put("DOS", attackMap.get("DOS")+1);
				}
				//Probe
				else if((attackType.equals("satan."))||(attackType.equals("ipsweep."))||
						(attackType.equals("nmap."))||(attackType.equals("portsweep."))){
					attackMap.put("Probe", (attackMap.get("Probe"))+1);

				}

				//R2L
				else if((attackType.equals("guess_passwd."))||(attackType.equals("ftp_write."))||
						(attackType.equals("imap."))||(attackType.equals("phf."))||
						(attackType.equals("multihop."))||(attackType.equals("warezmaster."))||
						(attackType.equals("warezclient."))||(attackType.equals("spy."))){
					attackMap.put("R2L", (attackMap.get("R2L"))+1);
				}
				//U2R
				else if((attackType.equals("buffer_overflow."))||(attackType.equals("loadmodule."))||
						(attackType.equals("rootkit."))||(attackType.equals("perl."))){
					attackMap.put("U2R", (attackMap.get("U2R"))+1);
				}
			}
			System.out.println("Cluster "+i);
			System.out.println("----------------");
			int c = 0;
			for (HashMap.Entry<String,Integer> entry : attackMap.entrySet()) {
				System.out.println(entry.getKey() + " -> " + entry.getValue());
				cluster_labels[i][c] = entry.getValue();c++;
			}
			System.out.println();
			System.out.println("----------------");
		}

		ArrayList<Integer> assign = new ArrayList<Integer>();
                ArrayList<String> label = new ArrayList<String>();
		//to get the total count of each attack type
                for(int i = 0; i < k; i++){
			for(int j=0; j<=4; j++){
				cluster_labels[4][j] += cluster_labels[i][j];
			}
                }

                int l;
		//to set the priorities for attack types based on their maximum occurences
                for(int i=0;i < k;i++){
                        int maximum = -1;int index = 0;
                        for( l = 0;l < 5;l++){
                                if(assign.contains(l))
                                        continue;
                                else{
                                        if(cluster_labels[4][l] > maximum){
                                                maximum = cluster_labels[4][l];
                                                index = l;
                                        }
                                }
                        }
                        assign.add(index);
                        switch(index){
                        case 0:label.add("U2R");
                        break;
                        case 1:label.add("DOS");
                        break;
                        case 2:label.add("Normal");
                        break;
                        case 3:label.add("Probe");
                        break;
                        case 4:label.add("R2L");
                        break;
                        }

                }

		//label the clusters based on the priority obtained
                Integer[] preference = new Integer[4];
                preference = assign.toArray(preference);
                assign = new ArrayList<Integer>();
		System.out.println("The clusters are: ");
		int clusterNo = -1;
		for(int i = 0;i < preference.length;i++){
			int minimum = -1;
			for(int j = 0;j <finalClusters.size();j++){
				if(assign.contains(j))
					continue;
				else{ 
					if(cluster_labels[j][preference[i]] > minimum){
						minimum = cluster_labels[j][preference[i]];
						clusterNo = j;
					}
				}
			}
			System.out.println(label.get(i) + " is Cluster "+clusterNo );
			assign.add(clusterNo);
		}

	}


	// Print a usage message and exit.
	private static void usage()
	{
		System.err.println ("Usage: java pj2 threads=<k>" +
				"kMeansClusteringSmp <DatSet> <seed>");
		throw new IllegalArgumentException();
	} 


}


