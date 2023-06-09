package com.example.seam.view.ui;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.assist.base.BaseApp;
import com.example.assist.base.NetworkActivity;
import com.example.seam.R;
import com.example.seam.databinding.LoginBinding;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;
import com.example.seam.view.ui.root.Root;
import com.example.seam.view.ui.student.Student;
import com.example.seam.view.ui.teacher.Teacher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录界面
 *
 * @e-mail:2036573698@qq.com
 */
public class Login extends NetworkActivity<LoginBinding> {
    SharedPreferences.Editor edit;
    private RetrofitServiceUtil serviceUtil;
    private Vibrator vibrator;
    private boolean isRefuse;

    @Override
    protected void onCreate() {
        edit = BaseApp.sp.edit();
        serviceUtil = RetrofitUtil.retrofitServiceUtil();
    }

    @Override
    protected void onStart() {
        super.onStart();



        // 获取sp中存储的username和password
        String username = BaseApp.sp.getString("username", "");
        String password = BaseApp.sp.getString("password", "");
        // 自动登录验证
        if (!username.isEmpty() && !password.isEmpty()) {
            // 对输入框进行fuzhi
            binding.username.setText(username);
            binding.password.setText(password);
            // 如果sp中保存username、password将进行自动登录
            GetLogin(username, password);
        } else {
            // 清空sp
            edit.clear();
            edit.commit();
        }



    }

    @Override
    protected void onObserveData() {
        // 权限申请
        Power();

        // 点击登录
        Login();
        //其他
        Other();
        // 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
        vibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
    }

    /**
     * 权限申请
     */
    private void Power() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !isRefuse) {
            // 先判断有没有权限
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1024);
            }
        }
    }

    /**
     * 带回授权结果
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1024 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 检查是否有权限
            if (Environment.isExternalStorageManager()) {
                isRefuse = false;
                // 授权成功
            } else {
                isRefuse = true;
                // 授权失败
            }
        }
    }

    /**
     * 进行登录网络访问判断用户名和密码
     */
    private void GetLogin(String username, String password) {
        // 初始化Login.RowsBean.UserBean
        com.example.seam.pojo.Login.RowsBean.UserBean userBean = new com.example.seam.pojo.Login.RowsBean.UserBean();
        // 进行存放数据
        userBean.setUsername(username);
        userBean.setPassword(password);
        serviceUtil
                .Login(userBean)
                .enqueue(new Callback<com.example.seam.pojo.Login>() {
                    @Override
                    public void onResponse(Call<com.example.seam.pojo.Login> call, Response<com.example.seam.pojo.Login> response) {
                        // 保存token
                        edit.putString("token", response.body().getRows().getToken());
                        edit.putString("username", username);
                        edit.putString("password", password);
                        edit.apply();
                        // 根据权限就行跳转页面
                        Jump(response.body().getRows().getUser().get(0).getType());
                    }

                    @Override
                    public void onFailure(Call<com.example.seam.pojo.Login> call, Throwable t) {
                    }
                });
    }

    /**
     * 法律责任
     * 注册
     * 找回密码
     * 点击复选按钮后面的文字实现复选框的选择与取消
     */
    private void Other() {
        // 法律责任
        binding.text2.setOnClickListener(v -> {
            // 进行弹窗显示《服务协议和隐私保护措施》
            new AlertDialog.Builder(this)
                    .setTitle("《服务协议和隐私保护措施》")
                    .setMessage("用户隐私协议\n" +
                            "尊敬的用户，欢迎阅读本协议：\n" +
                            "\n" +
                            "您的隐私对我们很重要。并且我们致力于保护您的隐私。因此，我们制定了隐私政策。本隐私政策解释了当您使用软件和服务时，我们如何处理您的信息并保护您的隐私，以及与您的信息有关的可用权利和选项。我们认为，您有权了解我们在使用软件和服务时可能收集和使用的信息的做法。如果您不同意此处设定的条款和条件，请不要使用该软件和服务。\n" +
                            "\n" +
                            "1.我们处理的信息以及处理您的信息的目的\n" +
                            "我们将处理两大类信息。我们的使命是不断改进我们的软件和服务，并为您提供新的或更好的体验。作为此任务的一部分，我们将您的信息用于以下目的。如果出现用于处理您的个人数据的任何新目的，我们将通过对本隐私政策进行相应的更改，在开始处理有关该新目的的信息之前通知您。\n" +
                            "\n" +
                            "1.1您提交的信息\n" +
                            "\n" +
                            "（1）相机和照片。通常，为了向您提供许多照片编辑器功能（例如模板，滤镜），您可以自愿授予我们访问相机或相册的权限，以便在您的移动设备中获取照片。我们将使用第三方提供的SDK处理照片。对于此类照片，我们既不会存储也不会与任何第三方共享。此信息对于您与我们之间的合同的适当履行是必要的。\n" +
                            "\n" +
                            "（2）处理此类信息的目的仅在于操作应用程序的人脸识别功能（包括但不限于衰老效果、性别转化效果、人脸融合效果、换脸效果、滤镜效果），相机和照片。为了使用该应用程序的某些功能，您可以自愿授予我们访问相机或相册的权限，以便在您的移动设备中获取照片。我们可能会通过使用Photo Kit API来收集您在使用该应用程序时上传或提交的照片，图片和其他数据。此信息对于您与我们之间的合同的适当履行是必要的。\n" +
                            "\n" +
                            "（3）支持相关信息。如果您通过我们的支持部分或通过电子邮件与我们联系，我们可能会收集您的联系信息，您的投诉或反馈以及您自愿提供的与此支持有关的任何信息。此类信息仅用于为您提供支持服务，不会与通过软件和服务从您那里收集的任何其他信息相关联。 您提交的信息的目的是：\n" +
                            " （1）操作和更新软件和服务；\n" +
                            " （2）改进和定制软件和服务及相关服务；\n" +
                            " （3）维护，测试和监视软件的质量和操作；\n" +
                            " （4）为您提供支持并处理您的投诉和/或反馈；\n" +
                            " （5）在涉及您与软件和服务有关的纠纷时采取任何措施；\n" +
                            " （6）遵守并满足任何适用的法律法规，法律程序或可执行的政府要求。\n" +
                            "\n" +
                            "1.2自动处理的信息\n" +
                            "\n" +
                            "（1）当您使用软件和服务时，我们将使用Xcode API收集以下信息：\n" +
                            "-通用唯一标识符（UUID），移动设备的类型以及安装在移动设备上的应用程序和操作系统版本；\n" +
                            "-国家，语言；\n" +
                            "\n" +
                            "（2）元数据。元数据可以描述照片或其他文件中包含的位置信息和文件创建时间，或者照片中字符的粗略特征以及照片中包含的其他内容。我们使用照片元数据为您提供某些功能，包括精确的“面部识别”，“关键点识别”以及其他基于这些基本功能的自定义功能。\n" +
                            "\n" +
                            "（3）付款数据。我们可能收集有关您的订购单的信息，例如购买商品，订购时间，订单状态，付款时间。我们不收集，也不对收集付款细节或保证安全负责。付款信息是使用第三方公司（例如Apple和Google）处理和存储的。可通过以下网站与这些公司联系：https：//http://www.apple.com或https://play.google.com。在输入您的个人付款详细信息之前，我们建议您阅读并熟悉这些第三方公司的隐私政策。 当您开始使用软件和服务时，将通过自动数据处理技术自动处理信息。自动处理的信息的目的是：\n" +
                            "（1）操作和更新软件和服务，并为您提供现有和新的功能和特性；\n" +
                            "（2）改进和定制软件和服务及相关服务；\n" +
                            "（3）维护，测试，监视和改善软件的质量和操作；\n" +
                            "（4）强制执行适用于软件和服务的条款和条件，并防止，检测和调查欺诈，安全漏洞，潜在的禁止或非法活动及其滥用，保护我们的商标并执行我们的使用条款。\n" +
                            "\n" +
                            "2.保护个人信息\n" +
                            "我们采取预防措施，包括行政，技术和物理措施，以保护您的个人信息免遭丢失，盗窃和滥用以及未经授权的访问，披露，更改和破坏。\n" +
                            "\n" +
                            "确保您的个人信息安全；我们会向所有员工传达我们的隐私和安全准则，并严格执行公司内部的隐私保护措施。\n" +
                            "\n" +
                            "不幸的是，互联网上的传输方法或电子存储方法都不是100％安全的。我们尽力保护您的个人信息，但是，我们不能保证其绝对安全。如果您的个人信息因安全受到破坏而被盗用，我们将立即按照适用法律通知您。\n" +
                            "\n" +
                            "如果您对我们的软件和服务的安全性有任何疑问，可以通过下面显示的电子邮件与我们联系。\n" +
                            "\n" +
                            "3.与第三方共享信息\n" +
                            "除以下事件外，我们不会共享我们从您那里收集的任何个人识别信息：\n" +
                            "\n" +
                            "3.1如果法律要求我们披露您的信息，我们可能会视需要与执法机构或其他主管当局和任何第三方共享您的信息（例如，检测，预防或以其他方式解决欺诈，安全或技术问题） ；回应要求或满足任何法律程序，传票或政府要求；或保护小组用户，合作伙伴或公众的权利，财产或人身安全）；\n" +
                            "\n" +
                            "3.2如果团队经历了业务过渡，例如另一家公司的合并或收购，合并，控制权变更，重组或出售其全部或部分资产，则您的信息将包含在转让的资产中。\n" +
                            "\n" +
                            "4.第三方内容\n" +
                            "某些软件和服务可能包括和/或使您能够向其中添加内容。内容的形式可以是按钮，小工具，指向第三方广告，网站，产品和服务（包括搜索服务）的链接，以及第三方提供给您的其他方式（统称为“第三方内容和服务” ”）。此类第三方内容和服务的使用受其隐私政策的约束。您应了解，使用第三方内容和服务可能使这些第三方能够访问，收集，存储和/或共享您的信息，包括您的PII和非个人身份信息。为澄清起见，我们不对此类第三方内容和服务进行审查，批准，监控，认可，保证或作出任何陈述，并且您访问任何第三方内容和服务的风险由您自行承担。对于这些第三方内容和服务的实践，您的使用或无法使用或无法使用这些信息，我们不承担任何责任。您明确免除我们因使用此类第三方内容和服务而引起的任何责任。我们建议您在使用或访问任何第三方内容和服务之前，请阅读其条款，条件和隐私策。\n" +
                            "\n" +
                            "5.将数据传输到您的区域之外\n" +
                            "我们在国际空间工作，并向世界各地的用户提供我们的软件和服务。我们和为软件和服务提供自动数据处理技术的第三方组织可能会将跨边界的自动处理的信息从您所在的国家或地区转移到世界其他国家或地区。\n" +
                            "\n" +
                            "根据法律要求，在此您位于EEA的任何地方，在此明确表示同意您在使用软件和服务后将您的数据传输到EEA以外的地方。您可以随时通过以下电子邮件与我们联系，以撤回您的同意。\n" +
                            "\n" +
                            "6.我们将如何保留您的个人数据\n" +
                            "我们通常会保留您的个人信息，直到您与我们之间履行合同并遵守我们的法律义务。如果您不再希望我们使用我们实际访问和存储的信息，则可以要求我们擦除您的个人信息并关闭您的帐户。\n" +
                            "\n" +
                            "特别的，在本产品中的面部数据，仅用于功能使用过程中的识别、提取、操作，不做任何存储。\n" +
                            "\n" +
                            "但是，如果为了遵守法律义务（征税，会计，审计）或为了维护安全和数据备份设置，防止欺诈或其他恶意行为而需要信息，某些数据可能仍会存储一段时间。\n" +
                            "\n" +
                            "7.您的数据权利\n" +
                            "您有权访问，修改，更正或删除我们可能收集的任何个人数据。为了行使这项权利，请通过电子邮件customer@linghit.com与我们联系。如果您在欧洲经济区，则您有权（除少数例外情况）：（i）请求访问和更正或删除您的个人信息； （ii）获得处理限制或反对处理您的个人信息； （iii）要求以数字格式提供您的个人信息的副本。您也有权向EEA的本地数据保护机构投诉有关处理您的个人信息的投诉。要行使这些权利，请通过电子邮件customer@xiyan.com与我们联系。\n" +
                            "\n" +
                            "9.更改隐私政策\n" +
                            "本隐私政策可能会不时更改。任何更改都将在软件界面上发布。您持续使用软件和服务将被视为您接受此类更新。\n" +
                            "\n" +
                            "10.隐私问题\n" +
                            "在本协议中未声明的其他一切权利，仍归本公司所有。本公司保留对本协议的最终解释权利。\n" +
                            "\n" +
                            "如果您还有其他问题和建议，可以通过电子邮件2036573698@qq.com联系我们。")
                    .create()
                    .show();
        });
        // 注册
        binding.register.setOnClickListener(v -> {
            // 进行弹窗提示
            new AlertDialog.Builder(this)
                    .setMessage("暂不支持注册，请联系管理员分配密码！")
                    .create()
                    .show();
        });
        // 找回密码
        binding.retrievePassword.setOnClickListener(v -> {
            // 进行弹窗提示
            new AlertDialog.Builder(this)
                    .setMessage("暂不支持找回密码，请联系管理员重置密码！")
                    .create()
                    .show();
        });
        //点击复选按钮后面的文字实现复选框的选择与取消
        binding.text1.setOnClickListener(v -> {
            // 复选框的状态
            if (binding.checkBox.isChecked()) {
                // 复选框（选中），点击实现复选框（取消）
                binding.checkBox.setChecked(false);
            } else {
                // 复选框（取消），点击实现复选框（选中）
                binding.checkBox.setChecked(true);
            }
        });
    }

    /**
     * 点击登录
     */
    private void Login() {
        binding.loginIn.setOnClickListener(v -> {
            // 判断是否同意《服务协议和隐私保护措施》
            if (binding.checkBox.isChecked()) {
                // 同意《服务协议和隐私保护措施》，进行登录
                if (binding.username.getText().toString().isEmpty() ||
                        binding.password.getText().toString().isEmpty()) {
                    // 进行提示
                    Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 登录
                    GetLogin(binding.username.getText().toString(), binding.password.getText().toString());
                }
            } else {
                // 四个参数（停止、开启、停止、开启）（-1不重复，非-1为从pattern的指定下标开始重复）
                // 停止一秒，震动一秒，停止一秒，震动一秒，不重复
                vibrator.vibrate(new long[]{0, 100, 0, 0}, -1);
                // 不同意《服务协议和隐私保护措施》，抖动checkBox、text1、text2控件
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                binding.checkBox.startAnimation(animation);
                binding.text1.startAnimation(animation);
                binding.text2.startAnimation(animation);
            }
        });
    }

    /**
     * 根据账号权限就行跳转页面
     *
     * @param type
     */
    private void Jump(int type) {
        switch (type) {
            case 0:
                // 管理人员
                startActivity(new Intent(Login.this, Root.class));
                finish();
                break;
            case 1:
                // 老师
                startActivity(new Intent(Login.this, Teacher.class));
                finish();
                break;
            case 2:
                // 学生
                startActivity(new Intent(Login.this, Student.class));
                finish();
                break;
        }
    }
}
