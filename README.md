# recyclerview
关于手动添加RecyclerView列表项 导致Item里的EditText数据混乱的问题
如果没有手动添加Item这个功能，比较好处理一些，最简单的方法禁止复用`holder.setIsRecyclable(false);`，但如果有手动添加Item，或者要监听EditText的addTextChangedListener，这时就是禁止复用也没有效果。
此时，就得使用绑定tag的方法：

第一、为列表实体类添加一个属性uid，作为一个唯一标志，再创建实体类Data的时候，为这个属性设置一个唯一值

```
  //如为其设置一个单位为纳秒的时间戳
  Data data = new Data();
  data.setUid(System.nanoTime());
```
第二、在adapter里设置tag

```
    @Override
    public void onBindViewHolder(final CustomAdapter.CustomViewHolder holder, final int position) {
        final Data data = list.get(position);
        holder.iv.setImageResource(data.getResId());
        holder.et.setTag(data.getUid());//获取唯一的uid设置到tag
        holder.et.setText(data.getStr());

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable editable) {
            	//取出ViewHolder中的tag和本条数据的uid进行比较，如果不相等就代表该ViewHolder是复用的，监听里不做处理
                if (holder.et.getTag() instanceof Long && (long)holder.et.getTag() == data.getUid() && holder.et.hasFocus()){
                    data.setStr(editable.toString());
                }
            }
        };
        holder.et.addTextChangedListener(textWatcher);
    }
```
第三、再新加Item的时候，要注意给Data设置数据，主要是uid。如果没有手动添加功能，这条就不用考虑

```
  //添加一条item
  btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();
                data.setUid(System.nanoTime());
                data.setResId(iv[0]);
                list.add(0,data);
                adapter.notifyDataSetChanged();
            }
        });
```
