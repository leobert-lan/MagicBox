/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package osp.leobert.android.magicboxsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ListView menuListView;

    List<SampleCase> sampleCases = Arrays.asList(
            new SampleCase("simple test", SampleActivity.class),
            new SampleCase("nested test-1", NestedDemo1Activity.class),
            new SampleCase("nested test-2", NestedDemo2Activity.class)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuListView = findViewById(R.id.list_menu);
        menuListView.setAdapter(new Adapter(sampleCases));

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuActivity.this, sampleCases.get(position).getActivityClz());
                startActivity(intent);
            }
        });
    }


    private static class Adapter extends BaseAdapter {
        private List<SampleCase> sampleCases;

        public Adapter(List<SampleCase> sampleCases) {
            this.sampleCases = sampleCases;
        }

        @Override
        public int getCount() {
            return sampleCases.size();
        }

        @Override
        public Object getItem(int position) {
            return sampleCases.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, null);
                holder = new ViewHolder();
                holder.textView = convertView.findViewById(R.id.menu_item_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textView.setText(sampleCases.get(position).getName());

            return convertView;
        }

        static class ViewHolder {
            TextView textView;
        }
    }

    private static final class SampleCase {
        private String name;
        private Class<? extends Activity> activityClz;

        public SampleCase(String name, Class<? extends Activity> activityClz) {
            this.name = name;
            this.activityClz = activityClz;
        }

        public String getName() {
            return name;
        }

        public Class<? extends Activity> getActivityClz() {
            return activityClz;
        }
    }
}
