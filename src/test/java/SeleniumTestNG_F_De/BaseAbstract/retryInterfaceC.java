package SeleniumTestNG_F_De.BaseAbstract;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class retryInterfaceC implements IRetryAnalyzer{

	// to rerun failed TCs, we have to add this listener in test(tesng test wherewe are defining thes)
	int count =0;
	int max=2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<max) {
			count++;
			return true;
		}
		return false;
	}
	
	

}
