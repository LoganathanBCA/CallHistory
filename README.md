# 📞 Samsung Duos Style Call Log App

## Problem Statement

My father wanted the old Samsung Duos-style call history where multiple calls from the same number are grouped like:

```
📞 9999999999 (3)
   🕒 09:45 AM
   🕒 01:22 PM
   🕒 07:10 PM
```

Modern Android shows each call as a separate entry—very cluttered and confusing.

## The Options

1. Download an app that does it ✅  
2. Build it myself 😅

Most would go with option 1, but my dad doesn’t even know how to open the Phone app—and doesn’t want to learn either. So I had no choice but to build an app just to display grouped call logs.

---

## Who Am I?

- 🎓 M.Sc. Computer Science graduate  
- 🧠 2K kid raised in the Gen-AI era  
- 🎯 Vibe coder  

---

## Development Struggles

- ❌ Android Studio was too heavy for my old laptop  
- ❌ No unlimited internet, so no emulator  
- ✅ Found AIDE (Android IDE) APK online (40MB lifesaver)  
- ✅ Built and ran Java code directly on my phone  

ChatGPT helped shape the logic. First, I got the call logs. Then, added tabs for **Incoming**, **Outgoing**, **Missed**, and **All** calls. Then I grouped them by **Today**, **Yesterday**, and dates like `7.6.2025`, `6.6.2025`, etc.

---

## Highlights

- Built entirely on phone using AIDE + Java  
- No PC. No emulator. Just mobile + logic  
- Grouped calls by day and number  
- Supports Tamil and multilingual numbers  
- No fancy UI libraries—just raw Android  

---

## What Went Wrong

- Contacts version broke the grouping (2023 calls showed up under "Today")  
- Permission issues caused crashes  
- Reverted to number-only version for accuracy and clean UI  

---

## TL;DR

✅ Created an old-school call log app (Samsung Duos style) using Java + AIDE  
🚫 No Android Studio, PC, or emulator  
📱 Just my phone, AIDE, and a reason: **Dad’s phone habits**

---


### 🔗 GitHub Repo

[👉 View Source Code](https://github.com/LoganathanBCA/CallHistory)
