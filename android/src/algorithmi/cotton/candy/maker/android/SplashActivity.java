/**
  * copyright ©2013-2014 ®Algorithmi™.
  *
  * @author ¶muneebahmad¶ (ahmadgallian@yahoo.com) 
  *
  * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  * THE SOFTWARE.
  * **/

package algorithmi.cotton.candy.maker.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 *
 * @author muneebahmad
 */
public class SplashActivity extends Activity implements View.OnClickListener {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        
        thread.start();
    }
    
    Thread thread = new Thread() {

        @Override
        public void run() {
            try {
                sleep(1000);
                Intent intent = new Intent(getApplicationContext(), AndroidLauncher.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                
            } finally {
                exitThread.start();
            }
        }
       
    };
    
    Thread exitThread = new Thread() {

        @Override
        public void run() {
            try {
                sleep(12000);
                finish();
            } catch (InterruptedException e) {
                
            } finally {
            }
        }
        
    };

    @Override
    public void onClick(View v) {
    }
    
    @Override
    public void onBackPressed() {
        
    }
    
}/** [end class]. */
