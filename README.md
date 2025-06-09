# 📞 Samsung Duos Style Call Log App

## 🤔 Problem Statement

So one fine day, my father said:  
> "I liked the old Samsung Duos phone. When someone called me three times, it showed **📞 9999999999 (3)**.  
> Now your modern phone shows **9999999999** over and over again. Where’s the grouping?!"

That’s when it hit me:  
Modern Android call history = Chaos ☠️  
Samsung Duos call log = Peace 🧘‍♂️

---

## ⚙️ Two Options

1. Find an app that does this  
2. Build one from scratch  

Normally, you'd choose option 1. But my dad?  
He doesn't want to learn how to open the Phone app, let alone install one.  
So the burden fell upon me: **build an app to show call logs—and only call logs.**

---

## 🙋‍♂️ Who Am I?

- 🎓 M.Sc. Computer Science Graduate  
- 🧠 2K kid, born in the Gen-AI boom  
- 🎯 Vibe coder™  
- ☕ Fueled by bad UI and strong opinions

---

## 🛠️ The Struggle Was Real

- 💻 My laptop: 4GB RAM, 2 usable Chrome tabs before fainting  
- 📴 Internet: Limited data, emulator out of the question  
- ❌ Android Studio: NOPE  
- ✅ Found AIDE (Android IDE) APK (40MB = life-changing)  
- 🤝 ChatGPT: My coding buddy throughout

😂 Exactly! Who would've thought displaying normal call logs would drag you into the world of Data Structures and Algorithms?

Grouping by number and date? → HashMap
Preserving order? → LinkedHashMap
Avoiding duplicates? → Set logic
Efficient lookup? → Map magic

Yes, this app is secretly a DSA lab project in disguise! Even ChatGPT had to pull out its HashMap wand to make it work!

Then got call logs working → But ungrouped  
Then added tabs: Incoming, Outgoing, Missed, All → Looked better  
But then…  
> Sorting was wild! Calls from 2023 appearing in "Today"! 😤

---

## 🎉 Features

- ✅ Grouped call logs like Samsung Duos
- ✅ Tabs for Incoming / Outgoing / Missed / All
- ✅ Call counts per number
- ✅ Time of each call
- ✅ Built completely on phone—no laptop, no emulator

---

## 🐛 What Went Wrong

- Giving contact access broke grouping logic  
  > “Why is a 2023 contact appearing in today’s log?!”  
- Crash after granting contact permission  
- App lost its clean, modern UI and became Android 2.3 nostalgia  
- Reverted to number-only mode → 💯 accurate and clean

---

## TL;DR

🛠️ Built a Samsung Duos-style call log app using Java + AIDE  
📵 No Android Studio, No PC, No Emulator  
📱 Only a phone, sheer will, and a confused dad  
☎️📇 “Turns out, building a basic call log app needed more HashMaps than my college DSA exam.” 🤯🗃️  
🤣 Spent hours just so my dad could say:  
> "Hmm… now this looks okay."

---

## 📸 Screenshot

![screenshot](https://github.com/LoganathanBCA/CallHistory/blob/main/call%20history%20app%20screenshot.jpg?raw=true)

---

## 🔗 GitHub Repo

👉 [LoganathanBCA/CallHistory](https://github.com/LoganathanBCA/CallHistory)

---

📝 _Yes, I could’ve installed an app—but where’s the story in that?_ 😂
