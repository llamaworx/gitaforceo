package com.gitaforceo.app.data

import com.gitaforceo.app.model.*
import java.util.Calendar

object GitaData {

    val chapters: List<Chapter> = listOf(
        Chapter(
            id = 1,
            name = "The Conflict of Arjuna",
            sanskritName = "Arjuna Vishada Yoga",
            summary = "Arjuna faces a crisis of conscience on the battlefield of Kurukshetra, paralysed by the prospect of fighting his own kin. This mirrors the dilemmas every leader faces when duty conflicts with personal bonds.",
            verseCount = 47,
            ceoInsight = "Every CEO faces an Arjuna moment \u2014 when the right strategic decision conflicts with personal loyalty. Chapter 1 teaches us that acknowledging the conflict is the first step toward resolution.",
            verses = listOf(
                Verse(
                    id = "1.1",
                    chapterId = 1,
                    verseNumber = 1,
                    sanskrit = "\u0927\u0930\u094d\u092e\u0915\u094d\u0937\u0947\u0924\u094d\u0930\u0947 \u0915\u0941\u0930\u0941\u0915\u094d\u0937\u0947\u0924\u094d\u0930\u0947 \u0938\u092e\u0935\u0947\u0924\u093e \u092f\u0941\u092f\u0941\u0924\u094d\u0938\u0935\u0903 \u0964\n\u092e\u093e\u092e\u0915\u093e\u0903 \u092a\u093e\u0923\u094d\u0921\u0935\u093e\u0936\u094d\u091a\u0948\u0935 \u0915\u093f\u092e\u0915\u0941\u0930\u094d\u0935\u0924 \u0938\u091e\u094d\u091c\u092f \u0965",
                    transliteration = "dharma-kshetre kuru-kshetre samavet\u0101 yuyutsava\u1e25\nm\u0101mak\u0101\u1e25 p\u0101\u1e47\u1e0dav\u0101\u015b caiva kim akurvata sa\u00f1jaya",
                    translation = "On the sacred field of Kurukshetra, assembled and eager to fight, what did my sons and the sons of Pandu do, O Sanjaya?",
                    commentary = "The blind king Dhritarashtra asks about the battlefield \u2014 a field of dharma (righteousness). His blindness is both physical and moral, representing leaders who refuse to see inconvenient truths.",
                    corporateWisdom = "A boardroom is a dharma-kshetra \u2014 a field where right and wrong compete. The CEO who refuses to see the full picture, like Dhritarashtra, makes decisions from blindness. True leadership begins with opening your eyes to all stakeholders.",
                    familyWisdom = "Family conflicts, like corporate ones, begin when we choose not to see. Dhritarashtra\u2019s denial about his sons\u2019 wrongdoing mirrors how family patriarchs sometimes turn a blind eye to dysfunction.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.ETHICS_INTEGRITY)
                ),
                Verse(
                    id = "1.32",
                    chapterId = 1,
                    verseNumber = 32,
                    sanskrit = "\u0915\u093f\u0902 \u0928\u094b \u0930\u093e\u091c\u094d\u092f\u0947\u0928 \u0917\u094b\u0935\u093f\u0928\u094d\u0926 \u0915\u093f\u0902 \u092d\u094b\u0917\u0948\u0930\u094d\u091c\u0940\u0935\u093f\u0924\u0947\u0928 \u0935\u093e \u0964",
                    transliteration = "ki\u1e41 no r\u0101jyena govinda ki\u1e41 bhogair j\u012bvitena v\u0101",
                    translation = "Of what use is a kingdom, O Krishna, or enjoyment, or even life itself?",
                    commentary = "Arjuna questions the very purpose of power and wealth when those he loves stand on the opposing side. This is the fundamental existential question every successful person must face.",
                    corporateWisdom = "This is the question every CEO must honestly answer: What is the purpose of all this wealth, power, and position if it costs you your relationships and inner peace? Success without significance is hollow.",
                    familyWisdom = "Many executives achieve everything professionally only to realise they have lost connection with their families. Arjuna\u2019s lament reminds us to ask: what good is the corner office if the dinner table is empty?",
                    themes = listOf(WisdomTheme.FAMILY_BALANCE, WisdomTheme.SELF_MASTERY)
                ),
                Verse(
                    id = "1.47",
                    chapterId = 1,
                    verseNumber = 47,
                    sanskrit = "\u090f\u0935\u092e\u0941\u0915\u094d\u0924\u094d\u0935\u093e\u0930\u094d\u091c\u0941\u0928\u0903 \u0938\u0919\u094d\u0916\u094d\u092f\u0947 \u0930\u0925\u094b\u092a\u0938\u094d\u0925 \u0909\u092a\u093e\u0935\u093f\u0936\u0924\u094d \u0964\n\u0935\u093f\u0938\u0943\u091c\u094d\u092f \u0938\u0936\u0930\u0902 \u091a\u093e\u092a\u0902 \u0936\u094b\u0915\u0938\u0902\u0935\u093f\u0917\u094d\u0928\u092e\u093e\u0928\u0938\u0903 \u0965",
                    transliteration = "evam uktv\u0101rjuna\u1e25 sa\u1e45khye rathopastha up\u0101vi\u015bat\nvis\u1e5bjya sa-\u015bara\u1e41 c\u0101pa\u1e41 \u015boka-sa\u1e41vigna-m\u0101nasa\u1e25",
                    translation = "Having spoken thus, Arjuna cast aside his bow and arrows and sat down on his chariot, his mind overwhelmed with grief.",
                    commentary = "Arjuna\u2019s collapse is not weakness \u2014 it is the necessary breakdown before breakthrough. Only by fully experiencing his doubt can he become ready for Krishna\u2019s transformative wisdom.",
                    corporateWisdom = "Burnout and decision paralysis are not signs of failure. They are signals that you need deeper wisdom. The strongest leaders are those who pause, acknowledge their vulnerability, and seek counsel before acting.",
                    familyWisdom = "Sometimes the bravest thing a family leader can do is admit they don\u2019t have all the answers. Arjuna\u2019s surrender of his weapons is really a surrender of his ego \u2014 the prerequisite for growth.",
                    themes = listOf(WisdomTheme.STRESS_RESILIENCE, WisdomTheme.DECISION_MAKING)
                )
            )
        ),
        Chapter(
            id = 2,
            name = "The Path of Wisdom",
            sanskritName = "Sankhya Yoga",
            summary = "Krishna begins his teaching by distinguishing the eternal soul from the temporary body, and introduces the concept of performing action without attachment to results \u2014 the cornerstone of enlightened leadership.",
            verseCount = 72,
            ceoInsight = "Chapter 2 is the CEO\u2019s operating manual. It teaches detachment from outcomes while giving your absolute best \u2014 the secret to sustainable high performance without burnout.",
            verses = listOf(
                Verse(
                    id = "2.14",
                    chapterId = 2,
                    verseNumber = 14,
                    sanskrit = "\u092e\u093e\u0924\u094d\u0930\u093e\u0938\u094d\u092a\u0930\u094d\u0936\u093e\u0938\u094d\u0924\u0941 \u0915\u094c\u0928\u094d\u0924\u0947\u092f \u0936\u0940\u0924\u094b\u0937\u094d\u0923\u0938\u0941\u0916\u0926\u0941\u0903\u0916\u0926\u093e\u0903 \u0964\n\u0906\u0917\u092e\u093e\u092a\u093e\u092f\u093f\u0928\u094b\u2019\u0928\u093f\u0924\u094d\u092f\u093e\u0938\u094d\u0924\u093e\u0902\u0938\u094d\u0924\u093f\u0924\u093f\u0915\u094d\u0937\u0938\u094d\u0935 \u092d\u093e\u0930\u0924 \u0965",
                    transliteration = "m\u0101tr\u0101-spar\u015b\u0101s tu kaunteya \u015b\u012bto\u1e63\u1e47a-sukha-du\u1e25kha-d\u0101\u1e25\n\u0101gam\u0101p\u0101yino \u2019nity\u0101s t\u0101\u1e41s titik\u1e63asva bh\u0101rata",
                    translation = "The contact of the senses with their objects, O son of Kunti, gives rise to cold and heat, pleasure and pain. They are transient \u2014 they come and go. Bear them patiently, O Bharata.",
                    commentary = "Krishna teaches that all sensory experiences \u2014 good and bad \u2014 are temporary. The wise person endures them without being disturbed, maintaining inner equilibrium.",
                    corporateWisdom = "Market booms and crashes, quarterly wins and losses \u2014 they all come and go. The CEO who maintains equanimity through bull and bear markets alike earns the lasting trust of stakeholders.",
                    familyWisdom = "Family life brings seasons of joy and seasons of difficulty. Neither lasts forever. The wisdom to endure tough phases without overreacting, and to enjoy good times without clinging, creates lasting family harmony.",
                    themes = listOf(WisdomTheme.STRESS_RESILIENCE, WisdomTheme.LEADERSHIP)
                ),
                Verse(
                    id = "2.47",
                    chapterId = 2,
                    verseNumber = 47,
                    sanskrit = "\u0915\u0930\u094d\u092e\u0923\u094d\u092f\u0947\u0935\u093e\u0927\u093f\u0915\u093e\u0930\u0938\u094d\u0924\u0947 \u092e\u093e \u092b\u0932\u0947\u0937\u0941 \u0915\u0926\u093e\u091a\u0928 \u0964\n\u092e\u093e \u0915\u0930\u094d\u092e\u092b\u0932\u0939\u0947\u0924\u0941\u0930\u094d\u092d\u0942\u0930\u094d\u092e\u093e \u0924\u0947 \u0938\u0919\u094d\u0917\u094b\u2019\u0938\u094d\u0924\u094d\u0935\u0915\u0930\u094d\u092e\u0923\u093f \u0965",
                    transliteration = "karma\u1e47y ev\u0101dhik\u0101ras te m\u0101 phale\u1e63u kad\u0101cana\nm\u0101 karma-phala-hetur bh\u016br m\u0101 te sa\u1e45go \u2019stv akarma\u1e47i",
                    translation = "You have the right to perform your duty, but you are not entitled to the fruits of your actions. Never consider yourself the cause of the results, and never be attached to inaction.",
                    commentary = "This is perhaps the most famous verse of the Gita. It does not advocate indifference, but rather total commitment to excellent work while releasing anxiety about outcomes.",
                    corporateWisdom = "This is the ultimate performance philosophy for CEOs: focus relentlessly on the quality of your decisions and execution, not on quarterly stock prices. When you detach from outcomes, paradoxically, better outcomes follow because fear no longer clouds your judgement.",
                    familyWisdom = "Raise your children with love and the best values you can, but release your attachment to who they become. Support your spouse fully without keeping score. The healthiest families are those where giving is unconditional.",
                    themes = listOf(WisdomTheme.DUTY_DHARMA, WisdomTheme.LEADERSHIP, WisdomTheme.SELF_MASTERY)
                ),
                Verse(
                    id = "2.48",
                    chapterId = 2,
                    verseNumber = 48,
                    sanskrit = "\u092f\u094b\u0917\u0938\u094d\u0925\u0903 \u0915\u0941\u0930\u0941 \u0915\u0930\u094d\u092e\u093e\u0923\u093f \u0938\u0919\u094d\u0917\u0902 \u0924\u094d\u092f\u0915\u094d\u0924\u094d\u0935\u093e \u0927\u0928\u091e\u094d\u091c\u092f \u0964\n\u0938\u093f\u0926\u094d\u0927\u094d\u092f\u0938\u093f\u0926\u094d\u0927\u094d\u092f\u094b\u0903 \u0938\u092e\u094b \u092d\u0942\u0924\u094d\u0935\u093e \u0938\u092e\u0924\u094d\u0935\u0902 \u092f\u094b\u0917 \u0909\u091a\u094d\u092f\u0924\u0947 \u0965",
                    transliteration = "yoga-stha\u1e25 kuru karm\u0101\u1e47i sa\u1e45ga\u1e41 tyaktv\u0101 dhana\u00f1jaya\nsiddhy-asiddhyo\u1e25 samo bh\u016btv\u0101 samatva\u1e41 yoga ucyate",
                    translation = "Perform your duties established in yoga, O Arjuna, abandoning attachment, and remaining equal in success and failure. Such equanimity is called yoga.",
                    commentary = "Krishna defines yoga not as physical postures, but as mental equanimity \u2014 treating success and failure with the same balanced mind. This is the foundation of effective action.",
                    corporateWisdom = "Equanimity is the CEO\u2019s superpower. When a product launch succeeds, don\u2019t let hubris set in. When it fails, don\u2019t let despair take hold. The leader who remains steady through both inspires confidence across the entire organisation.",
                    familyWisdom = "Bringing the same steady presence home \u2014 not riding emotional highs and lows \u2014 creates a stable environment where family members feel safe to grow and take risks of their own.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.STRESS_RESILIENCE)
                ),
                Verse(
                    id = "2.62",
                    chapterId = 2,
                    verseNumber = 62,
                    sanskrit = "\u0927\u094d\u092f\u093e\u092f\u0924\u094b \u0935\u093f\u0937\u092f\u093e\u0928\u094d\u092a\u0941\u0902\u0938\u0903 \u0938\u0919\u094d\u0917\u0938\u094d\u0924\u0947\u0937\u0942\u092a\u091c\u093e\u092f\u0924\u0947 \u0964\n\u0938\u0919\u094d\u0917\u093e\u0924\u094d\u0938\u091e\u094d\u091c\u093e\u092f\u0924\u0947 \u0915\u093e\u092e\u0903 \u0915\u093e\u092e\u093e\u0924\u094d\u0915\u094d\u0930\u094b\u0927\u094b\u2019\u092d\u093f\u091c\u093e\u092f\u0924\u0947 \u0965",
                    transliteration = "dhy\u0101yato vi\u1e63ay\u0101n pu\u1e41sa\u1e25 sa\u1e45gas te\u1e63\u016bp\u0101j\u0101yate\nsa\u1e45g\u0101t sa\u00f1j\u0101yate k\u0101ma\u1e25 k\u0101m\u0101t krodho \u2019bhij\u0101yate",
                    translation = "Dwelling on sense objects breeds attachment; attachment breeds desire; desire breeds anger.",
                    commentary = "Krishna maps the chain reaction from contemplation to destruction: thinking about objects leads to attachment, then desire, then anger when desire is unfulfilled, then delusion, then ruin.",
                    corporateWisdom = "This is the anatomy of corporate scandal. A CEO fixates on a competitor\u2019s success or an unrealistic target. Obsession breeds reckless desire, desire breeds frustration, and frustration breeds the ethical shortcuts that destroy companies.",
                    familyWisdom = "When we constantly compare our family to others \u2014 their wealth, their children\u2019s achievements, their lifestyle \u2014 we breed dissatisfaction that poisons our own home. Contentment is the antidote.",
                    themes = listOf(WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.SELF_MASTERY)
                )
            )
        ),
        Chapter(
            id = 3,
            name = "The Path of Action",
            sanskritName = "Karma Yoga",
            summary = "Krishna teaches that selfless action performed as a duty, without attachment, is superior to renunciation. Work itself becomes a form of worship when done with the right attitude.",
            verseCount = 43,
            ceoInsight = "For the CEO, Chapter 3 resolves the tension between ambition and detachment. You must act \u2014 but act as a steward, not an owner. This is the secret of servant leadership.",
            verses = listOf(
                Verse(
                    id = "3.8",
                    chapterId = 3,
                    verseNumber = 8,
                    sanskrit = "\u0928\u093f\u092f\u0924\u0902 \u0915\u0941\u0930\u0941 \u0915\u0930\u094d\u092e \u0924\u094d\u0935\u0902 \u0915\u0930\u094d\u092e \u091c\u094d\u092f\u093e\u092f\u094b \u0939\u094d\u092f\u0915\u0930\u094d\u092e\u0923\u0903 \u0964\n\u0936\u0930\u0940\u0930\u092f\u093e\u0924\u094d\u0930\u093e\u092a\u093f \u091a \u0924\u0947 \u0928 \u092a\u094d\u0930\u0938\u093f\u0926\u094d\u0927\u094d\u092f\u0947\u0926\u0915\u0930\u094d\u092e\u0923\u0903 \u0965",
                    transliteration = "niyata\u1e41 kuru karma tva\u1e41 karma jy\u0101yo hy akarma\u1e47a\u1e25\n\u015bar\u012bra-y\u0101tr\u0101pi ca te na prasiddhyed akarma\u1e47a\u1e25",
                    translation = "Perform your prescribed duty, for action is better than inaction. Even the maintenance of your body would be impossible through inaction.",
                    commentary = "Krishna firmly tells Arjuna that withdrawal is not an option. Renouncing the world is not the answer \u2014 transforming your relationship with action is.",
                    corporateWisdom = "A CEO cannot lead from the sidelines. Analysis paralysis and endless committee meetings are forms of inaction disguised as caution. The Gita demands engaged leadership \u2014 make the call, take responsibility, and move forward.",
                    familyWisdom = "Being present for your family is an action, not a passive state. Showing up to the school play, having dinner together, listening actively \u2014 these are prescribed duties that no amount of financial provision can replace.",
                    themes = listOf(WisdomTheme.DUTY_DHARMA, WisdomTheme.LEADERSHIP, WisdomTheme.DECISION_MAKING)
                ),
                Verse(
                    id = "3.21",
                    chapterId = 3,
                    verseNumber = 21,
                    sanskrit = "\u092f\u0926\u094d\u092f\u0926\u093e\u091a\u0930\u0924\u093f \u0936\u094d\u0930\u0947\u0937\u094d\u0920\u0938\u094d\u0924\u0924\u094d\u0924\u0926\u0947\u0935\u0947\u0924\u0930\u094b \u091c\u0928\u0903 \u0964\n\u0938 \u092f\u0924\u094d\u092a\u094d\u0930\u092e\u093e\u0923\u0902 \u0915\u0941\u0930\u0941\u0924\u0947 \u0932\u094b\u0915\u0938\u094d\u0924\u0926\u0928\u0941\u0935\u0930\u094d\u0924\u0924\u0947 \u0965",
                    transliteration = "yad yad \u0101carati \u015bre\u1e63\u1e6dhas tat tad evetaro jana\u1e25\nsa yat pram\u0101\u1e47a\u1e41 kurute lokas tad anuvartate",
                    translation = "Whatever a great person does, common people follow. Whatever standards they set, the world pursues.",
                    commentary = "Leaders set the tone not through policy memos but through personal example. Krishna reminds us that people watch what leaders do, not what they say.",
                    corporateWisdom = "Culture flows from the top. If the CEO cuts corners, the organisation will too. If the CEO demonstrates integrity even when it costs revenue, a culture of trust takes root. Your behaviour is your most powerful corporate policy.",
                    familyWisdom = "Children don\u2019t listen to lectures \u2014 they mirror behaviour. If you show kindness to your spouse, manage anger with grace, and treat everyone with respect, your children absorb these values naturally.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.TEAM_BUILDING)
                ),
                Verse(
                    id = "3.35",
                    chapterId = 3,
                    verseNumber = 35,
                    sanskrit = "\u0936\u094d\u0930\u0947\u092f\u093e\u0928\u094d\u0938\u094d\u0935\u0927\u0930\u094d\u092e\u094b \u0935\u093f\u0917\u0941\u0923\u0903 \u092a\u0930\u0927\u0930\u094d\u092e\u093e\u0924\u094d\u0938\u094d\u0935\u0928\u0941\u0937\u094d\u0920\u093f\u0924\u093e\u0924\u094d \u0964\n\u0938\u094d\u0935\u0927\u0930\u094d\u092e\u0947 \u0928\u093f\u0927\u0928\u0902 \u0936\u094d\u0930\u0947\u092f\u0903 \u092a\u0930\u0927\u0930\u094d\u092e\u094b \u092d\u092f\u093e\u0935\u0939\u0903 \u0965",
                    transliteration = "\u015brey\u0101n sva-dharmo vigu\u1e47a\u1e25 para-dharm\u0101t sv-anu\u1e63\u1e6dhit\u0101t\nsva-dharme nidhana\u1e41 \u015breya\u1e25 para-dharmo bhay\u0101vaha\u1e25",
                    translation = "It is far better to perform one\u2019s own duty imperfectly than to perform another\u2019s duty perfectly. Destruction in one\u2019s own duty is better than engagement in another\u2019s duty, for to follow another\u2019s path is dangerous.",
                    commentary = "Krishna teaches that authenticity trumps imitation. Each person has a unique role to play, and attempting to be someone else leads to inner conflict and failure.",
                    corporateWisdom = "Don\u2019t try to be Steve Jobs or Elon Musk. Find your own leadership style, rooted in your authentic strengths. The CEO who copies another\u2019s playbook creates a fragile, inauthentic culture. Authenticity in leadership is non-negotiable.",
                    familyWisdom = "Don\u2019t try to be the parent your parents were, or the spouse you see in movies. Each family is unique. Play your authentic role \u2014 as father, mother, son, daughter \u2014 rather than performing someone else\u2019s version of it.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.DUTY_DHARMA, WisdomTheme.LEADERSHIP)
                )
            )
        ),
        Chapter(
            id = 4,
            name = "The Path of Knowledge",
            sanskritName = "Jnana Karma Sanyasa Yoga",
            summary = "Krishna reveals the ancient lineage of this wisdom and teaches that true knowledge transforms action. The wise person sees inaction in action and action in inaction.",
            verseCount = 42,
            ceoInsight = "Chapter 4 is about knowledge as the ultimate competitive advantage. Not market data or business intelligence, but self-knowledge \u2014 understanding your own motivations, biases, and blind spots.",
            verses = listOf(
                Verse(
                    id = "4.7",
                    chapterId = 4,
                    verseNumber = 7,
                    sanskrit = "\u092f\u0926\u093e \u092f\u0926\u093e \u0939\u093f \u0927\u0930\u094d\u092e\u0938\u094d\u092f \u0917\u094d\u0932\u093e\u0928\u093f\u0930\u094d\u092d\u0935\u0924\u093f \u092d\u093e\u0930\u0924 \u0964\n\u0905\u092d\u094d\u092f\u0941\u0924\u094d\u0925\u093e\u0928\u092e\u0927\u0930\u094d\u092e\u0938\u094d\u092f \u0924\u0926\u093e\u0924\u094d\u092e\u093e\u0928\u0902 \u0938\u0943\u091c\u093e\u092e\u094d\u092f\u0939\u092e\u094d \u0965",
                    transliteration = "yad\u0101 yad\u0101 hi dharmasya gl\u0101nir bhavati bh\u0101rata\nabhyutth\u0101nam adharmasya tad\u0101tm\u0101na\u1e41 s\u1e5bj\u0101my aham",
                    translation = "Whenever there is a decline in righteousness and an increase in unrighteousness, O Arjuna, at that time I manifest myself.",
                    commentary = "Krishna declares that divine intervention occurs precisely when moral order breaks down. This is a promise that the universe self-corrects.",
                    corporateWisdom = "When corporate culture decays \u2014 when shortcuts become normal and ethics erode \u2014 the organisation needs a transformational leader to restore dharma. That leader could be you. The best turnaround CEOs are those who restore moral order, not just financial order.",
                    familyWisdom = "In every family, there comes a time when old patterns of dysfunction must be broken. Someone must stand up and say \u2018this stops with me.\u2019 That act of moral courage can transform generations.",
                    themes = listOf(WisdomTheme.CHANGE_MANAGEMENT, WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.LEADERSHIP)
                ),
                Verse(
                    id = "4.18",
                    chapterId = 4,
                    verseNumber = 18,
                    sanskrit = "\u0915\u0930\u094d\u092e\u0923\u094d\u092f\u0915\u0930\u094d\u092e \u092f\u0903 \u092a\u0936\u094d\u092f\u0947\u0926\u0915\u0930\u094d\u092e\u0923\u093f \u091a \u0915\u0930\u094d\u092e \u092f\u0903 \u0964\n\u0938 \u092c\u0941\u0926\u094d\u0927\u093f\u092e\u093e\u0928\u094d\u092e\u0928\u0941\u0937\u094d\u092f\u0947\u0937\u0941 \u0938 \u092f\u0941\u0915\u094d\u0924\u0903 \u0915\u0943\u0924\u094d\u0938\u094d\u0928\u0915\u0930\u094d\u092e\u0915\u0943\u0924\u094d \u0965",
                    transliteration = "karma\u1e47y akarma ya\u1e25 pa\u015byed akarma\u1e47i ca karma ya\u1e25\nsa buddhim\u0101n manu\u1e63ye\u1e63u sa yukta\u1e25 k\u1e5btsna-karma-k\u1e5bt",
                    translation = "One who sees inaction in action and action in inaction is wise among people and is accomplished in all actions.",
                    commentary = "This profound verse teaches paradoxical wisdom: sometimes the most powerful action looks like stillness, and sometimes frantic activity accomplishes nothing.",
                    corporateWisdom = "The CEO who sits quietly in a meeting, listening deeply, may be doing more than the one who dominates every discussion. Strategic patience \u2014 knowing when NOT to act \u2014 is often the highest form of leadership.",
                    familyWisdom = "Sometimes the most powerful thing a parent can do is be present without fixing. Sitting with a child in their pain, without offering solutions, is action in apparent inaction \u2014 and it builds the deepest bonds.",
                    themes = listOf(WisdomTheme.STRATEGIC_THINKING, WisdomTheme.SELF_MASTERY, WisdomTheme.DECISION_MAKING)
                ),
                Verse(
                    id = "4.38",
                    chapterId = 4,
                    verseNumber = 38,
                    sanskrit = "\u0928 \u0939\u093f \u091c\u094d\u091e\u093e\u0928\u0947\u0928 \u0938\u0926\u0943\u0936\u0902 \u092a\u0935\u093f\u0924\u094d\u0930\u092e\u093f\u0939 \u0935\u093f\u0926\u094d\u092f\u0924\u0947 \u0964",
                    transliteration = "na hi j\u00f1\u0101nena sad\u1e5b\u015ba\u1e41 pavitram iha vidyate",
                    translation = "In this world, there is nothing as purifying as knowledge.",
                    commentary = "Krishna declares knowledge supreme \u2014 not information or data, but deep understanding that transforms the knower.",
                    corporateWisdom = "In the age of data overload, remember: data is not knowledge, and knowledge is not wisdom. The CEO who invests in deep understanding \u2014 of self, of customers, of market forces \u2014 builds an organisation that survives disruption.",
                    familyWisdom = "Invest in understanding your family members deeply \u2014 their fears, their dreams, their love languages. This knowledge, more than any material provision, is the foundation of a thriving family.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.STRATEGIC_THINKING)
                )
            )
        ),
        Chapter(
            id = 5,
            name = "The Path of Renunciation",
            sanskritName = "Karma Sanyasa Yoga",
            summary = "Krishna harmonises the paths of action and renunciation, teaching that true renunciation is not giving up work but giving up attachment to results while continuing to serve.",
            verseCount = 29,
            ceoInsight = "Chapter 5 answers the burning question for executives: Can I pursue ambitious goals and still find inner peace? Yes \u2014 by redefining what you renounce. Renounce the ego, not the enterprise.",
            verses = listOf(
                Verse(
                    id = "5.10",
                    chapterId = 5,
                    verseNumber = 10,
                    sanskrit = "\u092c\u094d\u0930\u0939\u094d\u092e\u0923\u094d\u092f\u093e\u0927\u093e\u092f \u0915\u0930\u094d\u092e\u093e\u0923\u093f \u0938\u0919\u094d\u0917\u0902 \u0924\u094d\u092f\u0915\u094d\u0924\u094d\u0935\u093e \u0915\u0930\u094b\u0924\u093f \u092f\u0903 \u0964\n\u0932\u093f\u092a\u094d\u092f\u0924\u0947 \u0928 \u0938 \u092a\u093e\u092a\u0947\u0928 \u092a\u0926\u094d\u092e\u092a\u0924\u094d\u0930\u092e\u093f\u0935\u093e\u092e\u094d\u092d\u0938\u093e \u0965",
                    transliteration = "brahma\u1e47y \u0101dh\u0101ya karm\u0101\u1e47i sa\u1e45ga\u1e41 tyaktv\u0101 karoti ya\u1e25\nlipyate na sa p\u0101pena padma-patram iv\u0101mbhas\u0101",
                    translation = "One who acts by dedicating all work to the Supreme, without attachment, is untouched by sin, as a lotus leaf is untouched by water.",
                    commentary = "The lotus metaphor is one of the Gita\u2019s most beautiful images: the lotus grows in muddy water yet remains pristine. Similarly, one can work in the material world without being corrupted by it.",
                    corporateWisdom = "The lotus CEO operates in the mud of corporate politics, competitive pressure, and financial markets without being stained. How? By anchoring every decision in a higher purpose beyond personal gain. Purpose is the CEO\u2019s protective coating.",
                    familyWisdom = "You can be deeply involved in family affairs \u2014 finances, disputes, in-law dynamics \u2014 without letting them consume your inner peace. Be like the lotus: present in the water, never soaked by it.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.STRESS_RESILIENCE)
                )
            )
        ),
        Chapter(
            id = 6,
            name = "The Path of Meditation",
            sanskritName = "Dhyana Yoga",
            summary = "Krishna teaches the practice of meditation and self-discipline, describing the characteristics of a person established in yoga \u2014 perfectly balanced, self-controlled, and serene.",
            verseCount = 47,
            ceoInsight = "Chapter 6 is the CEO\u2019s guide to mental fitness. Just as physical health requires exercise, mental clarity requires disciplined practice. Meditation is not a luxury \u2014 it is a leadership necessity.",
            verses = listOf(
                Verse(
                    id = "6.5",
                    chapterId = 6,
                    verseNumber = 5,
                    sanskrit = "\u0909\u0926\u094d\u0927\u0930\u0947\u0926\u093e\u0924\u094d\u092e\u0928\u093e\u0924\u094d\u092e\u093e\u0928\u0902 \u0928\u093e\u0924\u094d\u092e\u093e\u0928\u092e\u0935\u0938\u093e\u0926\u092f\u0947\u0924\u094d \u0964\n\u0906\u0924\u094d\u092e\u0948\u0935 \u0939\u094d\u092f\u093e\u0924\u094d\u092e\u0928\u094b \u092c\u0928\u094d\u0927\u0941\u0930\u093e\u0924\u094d\u092e\u0948\u0935 \u0930\u093f\u092a\u0941\u0930\u093e\u0924\u094d\u092e\u0928\u0903 \u0965",
                    transliteration = "uddhared \u0101tman\u0101tm\u0101na\u1e41 n\u0101tm\u0101nam avas\u0101dayet\n\u0101tmaiva hy \u0101tmano bandhur \u0101tmaiva ripur \u0101tmana\u1e25",
                    translation = "Elevate yourself by your own mind, and do not degrade yourself. The mind alone is the friend of the self, and the mind alone is the enemy of the self.",
                    commentary = "Krishna teaches radical self-responsibility. No external force lifts you up or brings you down \u2014 it is your own mind that is both your greatest ally and your most dangerous adversary.",
                    corporateWisdom = "No executive coach, board advisor, or strategy consultant can save a CEO from their own undisciplined mind. Self-mastery precedes market mastery. The inner game determines the outer game.",
                    familyWisdom = "Don\u2019t blame your spouse, children, or parents for your unhappiness. Your mind creates your experience. Master your own thoughts and reactions, and your family life transforms without anyone else needing to change.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.STRESS_RESILIENCE, WisdomTheme.LEADERSHIP)
                ),
                Verse(
                    id = "6.35",
                    chapterId = 6,
                    verseNumber = 35,
                    sanskrit = "\u0905\u0938\u0902\u0936\u092f\u0902 \u092e\u0939\u093e\u092c\u093e\u0939\u094b \u092e\u0928\u094b \u0926\u0941\u0930\u094d\u0928\u093f\u0917\u094d\u0930\u0939\u0902 \u091a\u0932\u092e\u094d \u0964\n\u0905\u092d\u094d\u092f\u093e\u0938\u0947\u0928 \u0924\u0941 \u0915\u094c\u0928\u094d\u0924\u0947\u092f \u0935\u0948\u0930\u093e\u0917\u094d\u092f\u0947\u0923 \u091a \u0917\u0943\u0939\u094d\u092f\u0924\u0947 \u0965",
                    transliteration = "asa\u1e41\u015baya\u1e41 mah\u0101-b\u0101ho mano durnigraha\u1e41 calam\nabhy\u0101sena tu kaunteya vair\u0101gye\u1e47a ca g\u1e5bhyate",
                    translation = "Undoubtedly, O mighty-armed one, the mind is restless and very difficult to control. But it can be restrained through practice and detachment, O son of Kunti.",
                    commentary = "Even Krishna acknowledges the difficulty of controlling the mind. But he gives the prescription: abhyasa (consistent practice) and vairagya (dispassion). There are no shortcuts.",
                    corporateWisdom = "Building focus and emotional regulation is like building a company \u2014 it requires relentless practice and the willingness to let go of distractions. The most effective CEOs have a daily discipline practice, whether it\u2019s meditation, journaling, or contemplation.",
                    familyWisdom = "Patience with family members is a practice, not a personality trait. If your mind races with work stress during family dinner, don\u2019t judge yourself \u2014 gently bring it back. Over time, presence becomes natural.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.STRESS_RESILIENCE)
                )
            )
        ),
        Chapter(
            id = 7,
            name = "Knowledge and Realisation",
            sanskritName = "Jnana Vijnana Yoga",
            summary = "Krishna reveals the nature of the divine and how to perceive the sacred in all of creation. He distinguishes between the lower material nature and the higher spiritual nature.",
            verseCount = 30,
            ceoInsight = "Chapter 7 challenges CEOs to see beyond quarterly numbers. There is a deeper order to the universe, and aligning your organisation with that order creates effortless success.",
            verses = listOf(
                Verse(
                    id = "7.11",
                    chapterId = 7,
                    verseNumber = 11,
                    sanskrit = "\u092c\u0932\u0902 \u092c\u0932\u0935\u0924\u093e\u0902 \u091a\u093e\u0939\u0902 \u0915\u093e\u092e\u0930\u093e\u0917\u0935\u093f\u0935\u0930\u094d\u091c\u093f\u0924\u092e\u094d \u0964\n\u0927\u0930\u094d\u092e\u093e\u0935\u093f\u0930\u0941\u0926\u094d\u0927\u094b \u092d\u0942\u0924\u0947\u0937\u0941 \u0915\u093e\u092e\u094b\u2019\u0938\u094d\u092e\u093f \u092d\u0930\u0924\u0930\u094d\u0937\u092d \u0965",
                    transliteration = "bala\u1e41 balavat\u0101\u1e41 c\u0101ha\u1e41 k\u0101ma-r\u0101ga-vivarjitam\ndharm\u0101viruddho bh\u016bte\u1e63u k\u0101mo \u2019smi bharatar\u1e63abha",
                    translation = "I am the strength of the strong, devoid of desire and attachment. I am desire itself when it is not contrary to dharma, O chief of the Bharatas.",
                    commentary = "Krishna reveals that divine strength is not about raw power but about power used without selfish attachment. Even desire itself is sacred when aligned with righteousness.",
                    corporateWisdom = "Corporate ambition is not wrong \u2014 it is sacred when aligned with dharma. The strength to pursue bold visions becomes divine when freed from ego and personal greed. Build empires that serve, not just extract.",
                    familyWisdom = "The desire to provide for your family, to see your children succeed, to maintain a loving home \u2014 these are dharmic desires. Honour them as sacred motivations, not distractions from spiritual life.",
                    themes = listOf(WisdomTheme.DUTY_DHARMA, WisdomTheme.LEADERSHIP, WisdomTheme.ETHICS_INTEGRITY)
                )
            )
        ),
        Chapter(
            id = 9,
            name = "The Royal Knowledge",
            sanskritName = "Raja Vidya Raja Guhya Yoga",
            summary = "Krishna reveals the most sovereign and confidential knowledge \u2014 the direct path to the divine through devotion, accessible to all regardless of birth, status, or education.",
            verseCount = 34,
            ceoInsight = "Chapter 9 democratises wisdom. True leadership knowledge is not exclusive to MBAs and pedigrees \u2014 it is available to anyone with sincerity. The best insights often come from the most unexpected sources.",
            verses = listOf(
                Verse(
                    id = "9.22",
                    chapterId = 9,
                    verseNumber = 22,
                    sanskrit = "\u0905\u0928\u0928\u094d\u092f\u093e\u0936\u094d\u091a\u093f\u0928\u094d\u0924\u092f\u0928\u094d\u0924\u094b \u092e\u093e\u0902 \u092f\u0947 \u091c\u0928\u093e\u0903 \u092a\u0930\u094d\u092f\u0941\u092a\u093e\u0938\u0924\u0947 \u0964\n\u0924\u0947\u0937\u093e\u0902 \u0928\u093f\u0924\u094d\u092f\u093e\u092d\u093f\u092f\u0941\u0915\u094d\u0924\u093e\u0928\u093e\u0902 \u092f\u094b\u0917\u0915\u094d\u0937\u0947\u092e\u0902 \u0935\u0939\u093e\u092e\u094d\u092f\u0939\u092e\u094d \u0965",
                    transliteration = "anany\u0101\u015b cintayanto m\u0101\u1e41 ye jan\u0101\u1e25 paryup\u0101sate\nte\u1e63\u0101\u1e41 nity\u0101bhiyukt\u0101n\u0101\u1e41 yoga-k\u1e63ema\u1e41 vah\u0101my aham",
                    translation = "To those who worship me with exclusive devotion, always thinking of me, I carry what they lack and preserve what they have.",
                    commentary = "Krishna makes an extraordinary promise: total dedication attracts total support. When you align fully with your purpose, the universe conspires to help you.",
                    corporateWisdom = "When a CEO is fully committed to a righteous mission \u2014 not hedging, not half-hearted \u2014 resources, talent, and opportunities seem to appear. Total commitment creates its own luck. But the mission must be worthy.",
                    familyWisdom = "When you give yourself fully to your family \u2014 truly present, deeply committed \u2014 life has a way of providing. The anxiety of \u2018not enough\u2019 dissolves when devotion is complete.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.DUTY_DHARMA, WisdomTheme.FAMILY_BALANCE)
                )
            )
        ),
        Chapter(
            id = 11,
            name = "The Universal Form",
            sanskritName = "Vishwarupa Darshana Yoga",
            summary = "Arjuna is granted divine vision to see Krishna\u2019s cosmic form \u2014 the entire universe contained within one being. This awe-inspiring revelation shows the interconnectedness of all existence.",
            verseCount = 55,
            ceoInsight = "Chapter 11 is about seeing the big picture \u2014 really big. The CEO who grasps the interconnectedness of all stakeholders, ecosystems, and generations makes decisions that stand the test of time.",
            verses = listOf(
                Verse(
                    id = "11.33",
                    chapterId = 11,
                    verseNumber = 33,
                    sanskrit = "\u0924\u0938\u094d\u092e\u093e\u0924\u094d\u0924\u094d\u0935\u092e\u0941\u0924\u094d\u0924\u093f\u0937\u094d\u0920 \u092f\u0936\u094b \u0932\u092d\u0938\u094d\u0935\n\u091c\u093f\u0924\u094d\u0935\u093e \u0936\u0924\u094d\u0930\u0942\u0928\u094d \u092d\u0941\u0919\u094d\u0915\u094d\u0937\u094d\u0935 \u0930\u093e\u091c\u094d\u092f\u0902 \u0938\u092e\u0943\u0926\u094d\u0927\u092e\u094d \u0964",
                    transliteration = "tasm\u0101t tvam utti\u1e63\u1e6dha ya\u015bo labhasva\njitv\u0101 \u015batr\u016bn bhu\u1e45k\u1e63va r\u0101jya\u1e41 sam\u1e5bddham",
                    translation = "Therefore arise and attain glory. Conquer your enemies and enjoy a prosperous kingdom.",
                    commentary = "After revealing the cosmic perspective, Krishna returns to the practical: now that you understand the grand design, get up and play your part in it with full vigour.",
                    corporateWisdom = "Vision without execution is hallucination. After the retreat, after the strategic planning session, after the moment of clarity \u2014 arise and execute. The market rewards those who combine cosmic thinking with ground-level action.",
                    familyWisdom = "Understanding your family\u2019s place in the larger fabric of life is beautiful. But you still need to get up and do the work \u2014 drive carpool, attend the PTA meeting, have the difficult conversation. Spiritual insight must translate to daily action.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.STRATEGIC_THINKING, WisdomTheme.DUTY_DHARMA)
                )
            )
        ),
        Chapter(
            id = 12,
            name = "The Path of Devotion",
            sanskritName = "Bhakti Yoga",
            summary = "Krishna describes the qualities of the ideal devotee \u2014 compassionate, content, forgiving, self-controlled, and friendly to all. This is also the portrait of the ideal leader.",
            verseCount = 20,
            ceoInsight = "Chapter 12 defines the \u2018servant leader\u2019 millennia before Robert Greenleaf coined the term. The qualities Krishna describes are the same ones that modern leadership research confirms as most effective.",
            verses = listOf(
                Verse(
                    id = "12.13",
                    chapterId = 12,
                    verseNumber = 13,
                    sanskrit = "\u0905\u0926\u094d\u0935\u0947\u0937\u094d\u091f\u093e \u0938\u0930\u094d\u0935\u092d\u0942\u0924\u093e\u0928\u093e\u0902 \u092e\u0948\u0924\u094d\u0930\u0903 \u0915\u0930\u0941\u0923 \u090f\u0935 \u091a \u0964\n\u0928\u093f\u0930\u094d\u092e\u092e\u094b \u0928\u093f\u0930\u0939\u0902\u0915\u093e\u0930\u0903 \u0938\u092e\u0926\u0941\u0903\u0916\u0938\u0941\u0916\u0903 \u0915\u094d\u0937\u092e\u0940 \u0965",
                    transliteration = "adve\u1e63\u1e6d\u0101 sarva-bh\u016bt\u0101n\u0101\u1e41 maitra\u1e25 karu\u1e47a eva ca\nnirmamo niraha\u1e41k\u0101ra\u1e25 sama-du\u1e25kha-sukha\u1e25 k\u1e63am\u012b",
                    translation = "One who is not envious but is a kind friend to all living entities, who does not think of himself as a proprietor, who is free from false ego, equal in both happiness and distress, and always forgiving.",
                    commentary = "Krishna paints the portrait of the evolved person \u2014 free from envy, kind, without possessiveness, humble, balanced, and forgiving. This is not weakness; it is the highest form of strength.",
                    corporateWisdom = "The greatest CEOs are not ego-driven empire builders but humble servant leaders. They don\u2019t hoard credit, they forgive mistakes that were made in good faith, and they treat the janitor with the same respect as the board chair.",
                    familyWisdom = "Imagine a home where nobody harbours envy, everyone is kind, nobody keeps score, ego is set aside, and forgiveness flows freely. This is the family Krishna envisions \u2014 and it starts with one person choosing to embody these qualities.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.TEAM_BUILDING, WisdomTheme.FAMILY_BALANCE, WisdomTheme.CONFLICT_RESOLUTION)
                )
            )
        ),
        Chapter(
            id = 14,
            name = "The Three Qualities of Nature",
            sanskritName = "Gunatraya Vibhaga Yoga",
            summary = "Krishna explains the three gunas \u2014 sattva (goodness), rajas (passion), and tamas (ignorance) \u2014 that govern all of nature and human behaviour. Understanding them is the key to self-awareness.",
            verseCount = 27,
            ceoInsight = "Chapter 14 gives CEOs a powerful framework for understanding organisational culture. Every company operates predominantly in one guna \u2014 and the CEO\u2019s job is to elevate the culture toward sattva.",
            verses = listOf(
                Verse(
                    id = "14.5",
                    chapterId = 14,
                    verseNumber = 5,
                    sanskrit = "\u0938\u0924\u094d\u0924\u094d\u0935\u0902 \u0930\u091c\u0938\u094d\u0924\u092e \u0907\u0924\u093f \u0917\u0941\u0923\u093e\u0903 \u092a\u094d\u0930\u0915\u0943\u0924\u093f\u0938\u092e\u094d\u092d\u0935\u093e\u0903 \u0964\n\u0928\u093f\u092c\u0927\u094d\u0928\u0928\u094d\u0924\u093f \u092e\u0939\u093e\u092c\u093e\u0939\u094b \u0926\u0947\u0939\u0947 \u0926\u0947\u0939\u093f\u0928\u092e\u0935\u094d\u092f\u092f\u092e\u094d \u0965",
                    transliteration = "sattva\u1e41 rajas tama iti gu\u1e47\u0101\u1e25 prak\u1e5bti-sambhav\u0101\u1e25\nnibadhnanti mah\u0101-b\u0101ho dehe dehinam avyayam",
                    translation = "Material nature consists of three modes \u2014 goodness, passion, and ignorance. When the eternal living entity comes in contact with nature, O mighty-armed Arjuna, he becomes conditioned by these modes.",
                    commentary = "The three gunas are the operating system of nature. Sattva brings clarity and peace, rajas brings restless ambition, and tamas brings inertia and confusion. We oscillate between all three.",
                    corporateWisdom = "Diagnose your company culture: Sattvic organisations make thoughtful, ethical decisions. Rajasic ones are hyperactive but burn out. Tamasic ones are stuck in bureaucratic inertia. The CEO\u2019s role is to cultivate sattva \u2014 clarity, integrity, and purposeful action.",
                    familyWisdom = "Notice your own moods: When you come home sattvic (calm, clear), family interactions flow. When rajasic (stressed, agitated), you create conflict. When tamasic (disengaged, numbed by screens), you\u2019re absent even when present. Choose sattva.",
                    themes = listOf(WisdomTheme.SELF_MASTERY, WisdomTheme.TEAM_BUILDING, WisdomTheme.CHANGE_MANAGEMENT)
                )
            )
        ),
        Chapter(
            id = 16,
            name = "Divine and Demonic Natures",
            sanskritName = "Daivasura Sampad Vibhaga Yoga",
            summary = "Krishna contrasts 26 divine qualities (fearlessness, truthfulness, compassion) with demonic traits (arrogance, anger, cruelty). This is the Gita\u2019s ethical framework for self-assessment.",
            verseCount = 24,
            ceoInsight = "Chapter 16 is the ultimate character audit for leaders. The divine qualities listed here map perfectly to what modern governance frameworks call \u2018fit and proper\u2019 criteria for board directors.",
            verses = listOf(
                Verse(
                    id = "16.1",
                    chapterId = 16,
                    verseNumber = 1,
                    sanskrit = "\u0905\u092d\u092f\u0902 \u0938\u0924\u094d\u0924\u094d\u0935\u0938\u0902\u0936\u0941\u0926\u094d\u0927\u093f\u0930\u094d\u091c\u094d\u091e\u093e\u0928\u092f\u094b\u0917\u0935\u094d\u092f\u0935\u0938\u094d\u0925\u093f\u0924\u093f\u0903 \u0964\n\u0926\u093e\u0928\u0902 \u0926\u092e\u0936\u094d\u091a \u092f\u091c\u094d\u091e\u0936\u094d\u091a \u0938\u094d\u0935\u093e\u0927\u094d\u092f\u093e\u092f\u0938\u094d\u0924\u092a \u0906\u0930\u094d\u091c\u0935\u092e\u094d \u0965",
                    transliteration = "abhaya\u1e41 sattva-sa\u1e41\u015buddhir j\u00f1\u0101na-yoga-vyavasthiti\u1e25\nd\u0101na\u1e41 dama\u015b ca yaj\u00f1a\u015b ca sv\u0101dhy\u0101yas tapa \u0101rjavam",
                    translation = "Fearlessness, purity of heart, steadfastness in knowledge and yoga, charity, self-control, sacrifice, study of the scriptures, austerity, and honesty.",
                    commentary = "Krishna begins listing the divine qualities, and fearlessness comes first. Not physical bravery, but the moral courage to do what is right regardless of consequences.",
                    corporateWisdom = "This is the CEO\u2019s character checklist: fearlessness to make tough calls, purity of intention, commitment to learning, generosity with credit, self-discipline, willingness to sacrifice personal comfort, continuous study, and above all \u2014 honesty.",
                    familyWisdom = "The same qualities that make a great leader make a great family member. Fearlessly honest conversations, generosity of spirit, self-control in anger, and the humility to keep learning as a spouse and parent.",
                    themes = listOf(WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.LEADERSHIP, WisdomTheme.SELF_MASTERY)
                ),
                Verse(
                    id = "16.21",
                    chapterId = 16,
                    verseNumber = 21,
                    sanskrit = "\u0924\u094d\u0930\u093f\u0935\u093f\u0927\u0902 \u0928\u0930\u0915\u0938\u094d\u092f\u0947\u0926\u0902 \u0926\u094d\u0935\u093e\u0930\u0902 \u0928\u093e\u0936\u0928\u092e\u093e\u0924\u094d\u092e\u0928\u0903 \u0964\n\u0915\u093e\u092e\u0903 \u0915\u094d\u0930\u094b\u0927\u0938\u094d\u0924\u0925\u093e \u0932\u094b\u092d\u0938\u094d\u0924\u0938\u094d\u092e\u093e\u0926\u0947\u0924\u0924\u094d\u0924\u094d\u0930\u092f\u0902 \u0924\u094d\u092f\u091c\u0947\u0924\u094d \u0965",
                    transliteration = "tri-vidha\u1e41 narakasyeda\u1e41 dv\u0101ra\u1e41 n\u0101\u015banam \u0101tmana\u1e25\nk\u0101ma\u1e25 krodhas tath\u0101 lobhas tasm\u0101d etat traya\u1e41 tyajet",
                    translation = "There are three gates to self-destruction and hell: lust, anger, and greed. Therefore, one should abandon all three.",
                    commentary = "Krishna identifies the three root causes of downfall with surgical precision: unchecked desire, uncontrolled anger, and insatiable greed. These three destroy individuals and institutions alike.",
                    corporateWisdom = "Every major corporate scandal traces back to one of these three: lust for power, anger at competitors or whistleblowers, or greed for more profit. The board that holds its CEO accountable on these three fronts protects the entire organisation.",
                    familyWisdom = "These same three forces \u2014 unchecked desire, explosive anger, and material greed \u2014 destroy families just as they destroy companies. Recognising them as enemies is the first step to protecting your home.",
                    themes = listOf(WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.SELF_MASTERY, WisdomTheme.CONFLICT_RESOLUTION)
                )
            )
        ),
        Chapter(
            id = 18,
            name = "The Path of Liberation",
            sanskritName = "Moksha Sanyasa Yoga",
            summary = "The grand finale. Krishna synthesises all his teachings and gives Arjuna the ultimate message: act according to your dharma with full devotion, and leave the rest to the divine.",
            verseCount = 78,
            ceoInsight = "Chapter 18 is the Gita\u2019s executive summary. It synthesises everything into one actionable framework: know your duty, do it with excellence, surrender the anxiety about outcomes, and trust the process.",
            verses = listOf(
                Verse(
                    id = "18.46",
                    chapterId = 18,
                    verseNumber = 46,
                    sanskrit = "\u092f\u0924\u0903 \u092a\u094d\u0930\u0935\u0943\u0924\u094d\u0924\u093f\u0930\u094d\u092d\u0942\u0924\u093e\u0928\u093e\u0902 \u092f\u0947\u0928 \u0938\u0930\u094d\u0935\u092e\u093f\u0926\u0902 \u0924\u0924\u092e\u094d \u0964\n\u0938\u094d\u0935\u0915\u0930\u094d\u092e\u0923\u093e \u0924\u092e\u092d\u094d\u092f\u0930\u094d\u091a\u094d\u092f \u0938\u093f\u0926\u094d\u0927\u093f\u0902 \u0935\u093f\u0928\u094d\u0926\u0924\u093f \u092e\u093e\u0928\u0935\u0903 \u0965",
                    transliteration = "yata\u1e25 prav\u1e5bttir bh\u016bt\u0101n\u0101\u1e41 yena sarvam ida\u1e41 tatam\nsva-karma\u1e47\u0101 tam abhyarcya siddhi\u1e41 vindati m\u0101nava\u1e25",
                    translation = "By worshipping through one\u2019s own work the One from whom all beings originate and by whom all this is pervaded, a person attains perfection.",
                    commentary = "Work itself becomes worship when performed with the right consciousness. This is Krishna\u2019s ultimate message: there is no separation between the sacred and the professional.",
                    corporateWisdom = "Your work IS your spiritual practice. The boardroom is your temple. Every decision made with integrity, every employee treated with dignity, every stakeholder served with care \u2014 this is worship through work. The CEO who understands this never burns out, because their work feeds their soul.",
                    familyWisdom = "Cooking dinner for your family, helping with homework, listening to your spouse\u2019s day \u2014 these mundane acts become sacred when performed with love and full attention. The divine is present in every act of service.",
                    themes = listOf(WisdomTheme.DUTY_DHARMA, WisdomTheme.SELF_MASTERY, WisdomTheme.LEADERSHIP)
                ),
                Verse(
                    id = "18.63",
                    chapterId = 18,
                    verseNumber = 63,
                    sanskrit = "\u0907\u0924\u093f \u0924\u0947 \u091c\u094d\u091e\u093e\u0928\u092e\u093e\u0916\u094d\u092f\u093e\u0924\u0902 \u0917\u0941\u0939\u094d\u092f\u093e\u0926\u094d\u0917\u0941\u0939\u094d\u092f\u0924\u0930\u0902 \u092e\u092f\u093e \u0964\n\u0935\u093f\u092e\u0943\u0936\u094d\u092f\u0948\u0924\u0926\u0936\u0947\u0937\u0947\u0923 \u092f\u0925\u0947\u091a\u094d\u091b\u0938\u093f \u0924\u0925\u093e \u0915\u0941\u0930\u0941 \u0965",
                    transliteration = "iti te j\u00f1\u0101nam \u0101khy\u0101ta\u1e41 guhy\u0101d guhyatara\u1e41 may\u0101\nvim\u1e5b\u015byaitad a\u015be\u1e63e\u1e47a yathecchasi tath\u0101 kuru",
                    translation = "Thus I have explained to you the most confidential of all knowledge. Deliberate on this fully, and then do as you wish.",
                    commentary = "In one of the most remarkable moments in all of scripture, the divine teacher does NOT command. After sharing the highest wisdom, Krishna says: now think about it, and decide for yourself. This is the ultimate respect for human free will.",
                    corporateWisdom = "The greatest leadership lesson in the Gita: after giving your team all the wisdom, information, and guidance you can \u2014 let them decide. True empowerment means trusting people to make their own choices. Counsel fully, then release control.",
                    familyWisdom = "This is the ultimate parenting verse: share your wisdom, give your best guidance, and then let your children choose their own path. The hardest and most loving act is saying \u2018now do as you wish\u2019 \u2014 and meaning it.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.SUCCESSION, WisdomTheme.FAMILY_BALANCE, WisdomTheme.DECISION_MAKING)
                ),
                Verse(
                    id = "18.66",
                    chapterId = 18,
                    verseNumber = 66,
                    sanskrit = "\u0938\u0930\u094d\u0935\u0927\u0930\u094d\u092e\u093e\u0928\u094d\u092a\u0930\u093f\u0924\u094d\u092f\u091c\u094d\u092f \u092e\u093e\u092e\u0947\u0915\u0902 \u0936\u0930\u0923\u0902 \u0935\u094d\u0930\u091c \u0964\n\u0905\u0939\u0902 \u0924\u094d\u0935\u093e\u0902 \u0938\u0930\u094d\u0935\u092a\u093e\u092a\u0947\u092d\u094d\u092f\u094b \u092e\u094b\u0915\u094d\u0937\u092f\u093f\u0937\u094d\u092f\u093e\u092e\u093f \u092e\u093e \u0936\u0941\u091a\u0903 \u0965",
                    transliteration = "sarva-dharm\u0101n parityajya m\u0101m eka\u1e41 \u015bara\u1e47a\u1e41 vraja\naha\u1e41 tv\u0101\u1e41 sarva-p\u0101pebhyo mok\u1e63ayi\u1e63y\u0101mi m\u0101 \u015buca\u1e25",
                    translation = "Abandon all varieties of duty and simply surrender unto me. I shall deliver you from all sinful reactions. Do not fear.",
                    commentary = "The Gita\u2019s final and most profound teaching: after all the philosophy, all the frameworks, all the practices \u2014 the ultimate path is surrender to a power greater than yourself. This is not fatalism; it is liberation.",
                    corporateWisdom = "After doing your absolute best \u2014 the analysis, the strategy, the execution \u2014 there comes a point where you must surrender to forces beyond your control. The market, technology shifts, black swan events. The CEO who can do their utmost and then let go sleeps well at night.",
                    familyWisdom = "Do your best for your family \u2014 love them, guide them, provide for them. And then surrender the outcome. You cannot control everything. The peace of a family built on love and surrender is unshakeable, even when life brings storms.",
                    themes = listOf(WisdomTheme.STRESS_RESILIENCE, WisdomTheme.SELF_MASTERY, WisdomTheme.SUCCESSION, WisdomTheme.FAMILY_BALANCE)
                ),
                Verse(
                    id = "18.78",
                    chapterId = 18,
                    verseNumber = 78,
                    sanskrit = "\u092f\u0924\u094d\u0930 \u092f\u094b\u0917\u0947\u0936\u094d\u0935\u0930\u0903 \u0915\u0943\u0937\u094d\u0923\u094b \u092f\u0924\u094d\u0930 \u092a\u093e\u0930\u094d\u0925\u094b \u0927\u0928\u0941\u0930\u094d\u0927\u0930\u0903 \u0964\n\u0924\u0924\u094d\u0930 \u0936\u094d\u0930\u0940\u0930\u094d\u0935\u093f\u091c\u092f\u094b \u092d\u0942\u0924\u093f\u0930\u094d\u0927\u094d\u0930\u0941\u0935\u093e \u0928\u0940\u0924\u093f\u0930\u094d\u092e\u0924\u093f\u0930\u094d\u092e\u092e \u0965",
                    transliteration = "yatra yoge\u015bvara\u1e25 k\u1e5b\u1e63\u1e47o yatra p\u0101rtho dhanur-dhara\u1e25\ntatra \u015br\u012br vijayo bh\u016btir dhruv\u0101 n\u012btir matir mama",
                    translation = "Wherever there is Krishna, the master of yoga, and wherever there is Arjuna, the supreme archer, there will certainly be fortune, victory, prosperity, and righteousness. This is my conviction.",
                    commentary = "The Gita\u2019s closing verse: where divine wisdom meets human capability, success is inevitable. It is the partnership of the eternal and the temporal that creates lasting victory.",
                    corporateWisdom = "When visionary wisdom (Krishna) meets disciplined execution (Arjuna), success follows. The best organisations pair strategic insight with operational excellence. Neither alone is sufficient \u2014 together, they are unstoppable.",
                    familyWisdom = "Where wisdom guides and dedication acts, families thrive. When partners combine one\u2019s vision with the other\u2019s practical strength \u2014 each contributing their best \u2014 the family becomes a force of prosperity and righteousness.",
                    themes = listOf(WisdomTheme.LEADERSHIP, WisdomTheme.STRATEGIC_THINKING, WisdomTheme.SUCCESSION)
                )
            )
        )
    )

    val allVerses: List<Verse>
        get() = chapters.flatMap { it.verses }

    fun versesForTheme(theme: WisdomTheme): List<Verse> {
        return allVerses.filter { it.themes.contains(theme) }
    }

    fun searchVerses(query: String): List<Verse> {
        val lowered = query.lowercase()
        return allVerses.filter { verse ->
            verse.translation.lowercase().contains(lowered) ||
            verse.commentary.lowercase().contains(lowered) ||
            verse.corporateWisdom.lowercase().contains(lowered) ||
            verse.familyWisdom.lowercase().contains(lowered)
        }
    }

    val dailyVerse: Verse
        get() {
            val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
            val index = (dayOfYear - 1) % allVerses.size
            return allVerses[index]
        }

    val appQuotes: List<String> = listOf(
        "You have the right to work, but never to the fruit of work.",
        "The mind is everything. What you think, you become.",
        "Change is the law of the universe.",
        "When meditation is mastered, the mind is unwavering like the flame of a lamp in a windless place.",
        "A person can rise through the efforts of their own mind; they can also degrade themselves. The mind is the friend and the enemy.",
        "There is neither this world, nor the world beyond, nor happiness for the one who doubts.",
        "The soul is neither born, and nor does it die.",
        "Set your heart upon your work but never its reward.",
        "Reshape yourself through the power of your will.",
        "The wise see knowledge and action as one."
    )

    val scenarios: List<ScenarioTemplate> = listOf(
        // Boardroom
        ScenarioTemplate(
            id = "hostile-takeover",
            title = "Hostile Takeover",
            icon = "shield_half",
            description = "Facing acquisition pressure or hostile bids",
            prompt = "I'm facing a hostile takeover attempt. The acquiring company is offering a premium but I believe our long-term vision is worth more. How does the Gita guide me through this?",
            category = ScenarioTemplate.Category.BOARDROOM
        ),
        ScenarioTemplate(
            id = "board-conflict",
            title = "Board Conflict",
            icon = "people_slash",
            description = "Disagreements among board members",
            prompt = "There is a deep conflict on my board. Two factions have opposing views on the company's strategic direction, and I'm caught in the middle as CEO. What wisdom does the Gita offer?",
            category = ScenarioTemplate.Category.BOARDROOM
        ),
        ScenarioTemplate(
            id = "ethical-dilemma",
            title = "Ethical Dilemma",
            icon = "scale",
            description = "When profit conflicts with principles",
            prompt = "I've discovered that a profitable business practice in my company may be causing harm to a community. Stopping it will hurt our quarterly numbers significantly. What does the Gita teach about this?",
            category = ScenarioTemplate.Category.BOARDROOM
        ),
        ScenarioTemplate(
            id = "succession",
            title = "Succession Planning",
            icon = "arrow_up_forward_circle",
            description = "Preparing for leadership transition",
            prompt = "I need to plan my succession as CEO. I have an internal candidate who is loyal but less talented, and an external candidate who is brilliant but unknown. How does the Gita approach this?",
            category = ScenarioTemplate.Category.BOARDROOM
        ),

        // Leadership
        ScenarioTemplate(
            id = "letting-go",
            title = "Letting Go of People",
            icon = "person_minus",
            description = "Making difficult personnel decisions",
            prompt = "I need to let go of a long-time executive who has been loyal but is now underperforming. They are also a personal friend. How does the Gita help me navigate this?",
            category = ScenarioTemplate.Category.LEADERSHIP
        ),
        ScenarioTemplate(
            id = "vision-doubt",
            title = "Doubting Your Vision",
            icon = "eye_warning",
            description = "When you question your own direction",
            prompt = "I've been leading this company for years but lately I'm questioning whether my vision is still right. The market is shifting and I feel uncertain. What would Krishna advise?",
            category = ScenarioTemplate.Category.LEADERSHIP
        ),
        ScenarioTemplate(
            id = "team-motivation",
            title = "Motivating Your Team",
            icon = "flame",
            description = "Inspiring people through tough times",
            prompt = "My team is demoralised after a failed product launch. Morale is at an all-time low. How can the Gita's teachings help me inspire them again?",
            category = ScenarioTemplate.Category.LEADERSHIP
        ),
        ScenarioTemplate(
            id = "decision-paralysis",
            title = "Decision Paralysis",
            icon = "branch",
            description = "Stuck between critical choices",
            prompt = "I have a critical decision to make \u2014 expand into a new market or double down on our core. Both paths have merits and risks. I've been deliberating for weeks. What does the Gita say about making difficult choices?",
            category = ScenarioTemplate.Category.LEADERSHIP
        ),

        // Personal
        ScenarioTemplate(
            id = "work-life",
            title = "Work-Life Balance",
            icon = "home_flag",
            description = "When work consumes your personal life",
            prompt = "My spouse says I'm never present even when I'm home. My children are growing up and I'm missing it. But the company demands everything. How does the Gita address this tension?",
            category = ScenarioTemplate.Category.PERSONAL
        ),
        ScenarioTemplate(
            id = "burnout",
            title = "Executive Burnout",
            icon = "battery_low",
            description = "Running on empty",
            prompt = "I'm exhausted. Despite external success, I feel empty inside. I dread Monday mornings and I've lost the passion I once had. What does the Gita teach about finding meaning when you're burned out?",
            category = ScenarioTemplate.Category.PERSONAL
        ),
        ScenarioTemplate(
            id = "family-business",
            title = "Family Business Tensions",
            icon = "family",
            description = "When family and business intertwine",
            prompt = "I run a family business and my siblings want to take the company in a different direction. Family dinners have become boardroom battles. How does the Gita \u2014 which itself is set amidst a family conflict \u2014 guide me?",
            category = ScenarioTemplate.Category.PERSONAL
        ),
        ScenarioTemplate(
            id = "legacy",
            title = "Defining Your Legacy",
            icon = "star_circle",
            description = "What you'll leave behind",
            prompt = "I'm in the later stage of my career and thinking about legacy. What does the Gita teach about building something that outlasts you? What truly matters in the end?",
            category = ScenarioTemplate.Category.PERSONAL
        ),

        // Crisis
        ScenarioTemplate(
            id = "company-crisis",
            title = "Company in Crisis",
            icon = "warning_triangle",
            description = "Navigating existential threats",
            prompt = "My company is in serious trouble \u2014 revenue is plummeting, key people are leaving, and the media is hostile. I feel like Arjuna on the battlefield. How does the Gita help a leader face potential ruin?",
            category = ScenarioTemplate.Category.CRISIS
        ),
        ScenarioTemplate(
            id = "public-scandal",
            title = "Reputation Crisis",
            icon = "newspaper",
            description = "When public trust is broken",
            prompt = "A scandal has broken involving my company. Even though I wasn't directly responsible, I'm the face of the organisation. Trust is shattered. What does the Gita teach about restoring righteousness?",
            category = ScenarioTemplate.Category.CRISIS
        ),
        ScenarioTemplate(
            id = "betrayal",
            title = "Betrayal by a Trusted Ally",
            icon = "shield_person",
            description = "When someone you trusted lets you down",
            prompt = "My most trusted deputy has been working against me behind the scenes. I feel deeply betrayed. The Gita opens with a similar conflict \u2014 what wisdom does it offer for this kind of personal and professional betrayal?",
            category = ScenarioTemplate.Category.CRISIS
        )
    )
}
